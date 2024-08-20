package com.heyrex.mauth.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.heyrex.mauth.R
import com.heyrex.mauth.core.utils.async
import com.heyrex.mauth.core.utils.isValidEmail
import com.heyrex.mauth.domain.model.User
import com.heyrex.mauth.domain.usecase.AuthCustomerUseCase
import com.heyrex.mauth.domain.usecase.AuthEmployeeUseCase
import com.heyrex.mauth.ui.BaseViewModel
import com.heyrex.mauth.ui.EventDelegate
import com.heyrex.mauth.ui.EventDelegateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val authCustomerUseCase: AuthCustomerUseCase,
    private val authEmployeeUseCase: AuthEmployeeUseCase,
) : BaseViewModel<LoginState, LoginIntent>(),
    EventDelegate<LoginEvent> by EventDelegateViewModel() {

    override var viewState: LoginState by mutableStateOf(LoginState())

    override fun processIntent(intent: LoginIntent) = when (intent) {
        is LoginIntent.EmailChanged -> onUserChanged(intent.email)
        is LoginIntent.PasswordChanged -> onPasswordChanged(intent.password)
        is LoginIntent.DoLogin -> onLogin(intent.type)
    }

    private fun onLogin(type: User.Profile) {

        if (validateFields()) {
            viewState = viewState.copy(loading = true)

            val callFlow = when (type) {
                User.Profile.EMPLOYEE ->
                    authEmployeeUseCase.execute(viewState.email, viewState.password)

                User.Profile.CUSTOMER ->
                    authCustomerUseCase.execute(viewState.email, viewState.password)
            }

            async(callFlow, {
                viewModelScope.launch {
                    viewState = viewState.copy(loading = false)
                    sendEvent(LoginEvent.NavigateToHome)
                }
            }, {
                viewState = viewState.copy(loading = false)
                viewModelScope.launch {
                    if (it is String) {
                        sendEvent(LoginEvent.Error(it))
                    } else if (it is Int) {
                        sendEvent(LoginEvent.ErrorKey(it))
                    }
                }
            })
        }
    }

    private fun validateFields(): Boolean {

        val user = viewState.email
        val password = viewState.password

        viewState = viewState.copy(
            errorUser = when {
                user.isBlank() -> R.string.emptyField
                !user.isValidEmail() -> R.string.emailInvalid
                else -> null
            },
            errorPass = if (password.isBlank()) R.string.emptyField else null
        )

        return hasValidFields()
    }

    private fun hasValidFields() = viewState.errorUser == null && viewState.errorPass == null

    private fun onUserChanged(user: String) {
        viewState = viewState.copy(
            errorUser = null,
            email = user
        )
    }

    private fun onPasswordChanged(password: String) {
        viewState = viewState.copy(
            errorPass = null,
            password = password
        )
    }

}