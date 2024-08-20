package com.heyrex.authprosegurapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Welcome(
    navController: NavHostController,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(NavigationItem.LoginCustomer.route) }
        ) {
            Text(text = stringResource(id = R.string.login_customer))
        }

        Button(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(NavigationItem.LoginEmployee.route) }
        ) {
            Text(text = stringResource(id = R.string.login_employee))
        }
    }
}

@Preview
@Composable
fun WelcomePreview() {
    Welcome(navController = rememberNavController())
}