package com.macaosoftware.nav3playground.moduleA

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
internal data object RouteAMain : NavKey

@Serializable
internal data class RouteAInternal1(val id: String) : NavKey

@Composable
internal fun ScreenA(
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit,
    onResult: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets.systemBars
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Greeting(name = "John Doe")
            Button(onClick = onNextClick) {
                Text(
                    text = "Go to Module A internal screen",
                )
            }
            Button(onClick = onResult) {
                Text(
                    text = "Deliver Result an go to Module B",
                )
            }
        }
    }
}

@Composable
private fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name! in A",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    Greeting("Android")
}
