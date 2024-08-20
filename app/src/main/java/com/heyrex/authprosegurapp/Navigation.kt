package com.heyrex.authprosegurapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.heyrex.mauth.domain.model.User
import com.heyrex.mauth.ui.login.Login
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current


    fun loginSuccess() {
        CoroutineScope(Dispatchers.Main).launch {
            snackbarHostState.showSnackbar(
                message = context.getString(R.string.login_success),
                duration = SnackbarDuration.Short
            )
        }
    }

    val showError: (String) -> Unit = { message ->
        CoroutineScope(Dispatchers.Main).launch {
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        NavHost(
            navController,
            startDestination = NavigationItem.Welcome.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavigationItem.Welcome.route) {
                Welcome(navController = navController)
            }
            composable(NavigationItem.LoginCustomer.route) {
                Login(
                    type = User.Profile.CUSTOMER,
                    onLoginSuccess = { loginSuccess() },
                    showError = showError
                )
            }
            composable(NavigationItem.LoginEmployee.route) {
                Login(
                    type = User.Profile.EMPLOYEE,
                    onLoginSuccess = { loginSuccess() },
                    showError = showError
                )
            }
        }
    }
}

enum class Screen {
    WELCOME,
    LOGIN_CUSTOMER,
    LOGIN_EMPLOYEE,
}

sealed class NavigationItem(val route: String) {
    object Welcome : NavigationItem(Screen.WELCOME.name)
    object LoginCustomer : NavigationItem(Screen.LOGIN_CUSTOMER.name)
    object LoginEmployee : NavigationItem(Screen.LOGIN_EMPLOYEE.name)
}