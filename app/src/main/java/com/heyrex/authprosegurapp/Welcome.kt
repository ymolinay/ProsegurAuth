package com.heyrex.authprosegurapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController

@Composable
fun Welcome(
    navController: NavHostController,
) {
    Column {
        Button(onClick = {
            navController.navigate(NavigationItem.LoginCustomer.route)
        }) {
            Text(text = stringResource(id = R.string.login_customer))
        }

        Button(onClick = {
            navController.navigate(NavigationItem.LoginEmployee.route)
        }) {
            Text(text = stringResource(id = R.string.login_employee))
        }
    }
}