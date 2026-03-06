package com.macaosoftware.nav3playground.moduleA.ui.view

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
import com.macaosoftware.nav3playground.common.results.ResultA

@Composable
internal fun ScreenA(
    modifier: Modifier = Modifier,
    screenAViewModel: ScreenAViewModel,
    onResult: (ResultA) -> Unit,
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
            Button(
                onClick = { onResult.invoke(ResultA("Screen A Result Data"))}
            ) {
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
