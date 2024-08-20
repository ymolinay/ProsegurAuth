package com.heyrex.mauth.ui.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.heyrex.mauth.R
import com.heyrex.mauth.core.composables.AppButton
import com.heyrex.mauth.core.composables.AppDivider
import com.heyrex.mauth.core.composables.AppLoader
import com.heyrex.mauth.core.composables.AppTextField
import com.heyrex.mauth.core.composables.AppTextFieldPassword
import com.heyrex.mauth.core.composables.AppWelcomeBack
import com.heyrex.mauth.core.composables.AppWelcomeCard
import com.heyrex.mauth.core.composables.CircularIcon
import com.heyrex.mauth.core.composables.EventProcessor
import com.heyrex.mauth.core.utils.formatEmailUsername
import com.heyrex.mauth.core.utils.isValidEmail
import com.heyrex.mauth.core.utils.textSimple
import com.heyrex.mauth.core.utils.titleBold
import com.heyrex.mauth.domain.model.User

@Composable
fun Login(
    viewModel: LoginViewModel = hiltViewModel(),
    type: User.Profile = User.Profile.CUSTOMER,
    onLoginSuccess: () -> Unit,
    showError: (String) -> Unit = {}
) {

    val state = viewModel.viewState

    LoginContent(
        loading = state.loading,
        type = type,
        email = state.email,
        password = state.password,
        onEmailChanged = {
            viewModel.sendIntent(LoginIntent.EmailChanged(it))
        },
        onPasswordChanged = {
            viewModel.sendIntent(LoginIntent.PasswordChanged(it))
        },
        onLogin = {
            viewModel.sendIntent(
                LoginIntent.DoLogin(state.email, state.password, type)
            )
        }
    )

    EventProcessor(viewModelEventDelegate = viewModel) { event ->
        when (event) {
            is LoginEvent.NavigateToHome -> {
                onLoginSuccess.invoke()
            }

            is LoginEvent.ErrorKey -> {
                showError(stringResource(id = event.key))
            }

            else -> {}
        }
    }

}

@Composable
private fun LoginContent(
    loading: Boolean,
    type: User.Profile,
    email: String,
    password: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLogin: () -> Unit
) {

    AnimatedVisibility(
        !loading,
        enter = fadeIn(initialAlpha = 0.3f),
        exit = fadeOut()
    ) {
        AppWelcomeBack {
            Column(
                modifier = Modifier
                    .padding(top = 180.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = R.string.login_title, type.value),
                    style = titleBold.copy(fontSize = 24.sp),
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                )
                AppWelcomeCard {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {

                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularIcon(
                                imageVector = Icons.Default.Person,
                                backgroundColor = Color.LightGray,
                                iconColor = Color.White,
                                size = 60.dp
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            ) {
                                Text(
                                    text = email.formatEmailUsername(),
                                    style = textSimple.copy(fontSize = 16.sp)
                                )
                                Text(
                                    text = email,
                                    style = titleBold.copy(fontSize = 16.sp)
                                )
                            }
                        }
                        AppDivider(height = 24.dp)
                        AppTextField(
                            value = email,
                            placeholder = stringResource(id = R.string.login_name),
                            onValueChange = onEmailChanged
                        )
                        AppDivider(height = 24.dp)
                        AppTextFieldPassword(
                            value = password,
                            placeholder = stringResource(id = R.string.login_password),
                            onValueChange = onPasswordChanged
                        )
                        AppDivider(height = 12.dp)
                        AppButton(
                            text = stringResource(id = R.string.welcome_continue),
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = { onLogin.invoke() },
                            enabled = email.isValidEmail() && password.isNotBlank(),
                        )
                        AppDivider(height = 24.dp)
                        Text(
                            text = stringResource(id = R.string.welcome_forgot_password),
                            style = titleBold.copy(fontSize = 16.sp),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }

    AnimatedVisibility(
        loading,
        enter = fadeIn(initialAlpha = 0.3f),
        exit = fadeOut()
    ) {
        AppLoader()
    }
}