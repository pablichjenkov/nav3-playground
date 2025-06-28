package com.macaosoftware.nav3playground.moduleB

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
internal data object RouteBMain : NavKey

@Serializable
private data class RouteB(val id: String) : NavKey

@Composable
fun ScreensB(modifier: Modifier = Modifier) {
    Greeting("Pablo")
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name! in B",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    //Nav3PlaygroundTheme {
    Greeting("Android")
    //}
}