package com.heyrex.mauth.ui.login

import androidx.annotation.StringRes
import com.heyrex.mauth.domain.model.User

data class LoginState(
    val loading: Boolean = false,
    val email: String = "",
    val password: String = "",
    @StringRes val errorUser: Int? = null,
    @StringRes val errorPass: Int? = null
)

sealed class LoginIntent{
    class EmailChanged(val email: String) : LoginIntent()
    class PasswordChanged(val password: String) : LoginIntent()
    class DoLogin(val email: String, val password: String, val type: User.Profile) : LoginIntent()
}

sealed class LoginEvent{
    object NavigateToHome : LoginEvent()
    data class Error(val message: String) : LoginEvent()
    data class ErrorKey(@StringRes val key: Int) : LoginEvent()
}