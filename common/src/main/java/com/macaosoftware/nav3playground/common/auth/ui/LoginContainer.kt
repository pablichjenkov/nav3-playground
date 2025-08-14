package com.macaosoftware.nav3playground.common.auth.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginContainer(
    modifier: Modifier = Modifier,
    loginContainerCallback: LoginContainerCallback
    ) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login Screen",
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(height = 48.dp))
            Button(
                onClick = {
                    loginContainerCallback.onResult(true)
                }
            ) {
                Text("Submit Success")
            }
            Spacer(modifier = Modifier.height(height = 48.dp))
            Button(
                onClick = {
                    loginContainerCallback.onResult(false)
                }
            ) {
                Text("Submit Error")
            }
            Spacer(modifier = Modifier.height(height = 48.dp))
            Button(
                onClick = {
                    loginContainerCallback.goToCreateAccount()
                }
            ) {
                Text("Go to Create Account")
            }
            Spacer(modifier = Modifier.height(height = 48.dp))
            Button(
                onClick = {
                    loginContainerCallback.goToForgotPassword()
                }
            ) {
                Text("Go to Forgot Password")
            }
        }

    }
}

class LoginContainerCallback (
    val onResult: (Boolean) -> Unit,
    val goToCreateAccount: () -> Unit,
    val goToForgotPassword: () -> Unit,
)