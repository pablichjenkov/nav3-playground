package com.macaosoftware.nav3playground.moduleB

import android.graphics.Camera
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun ScreensB(
    modifier: Modifier = Modifier,
    onExit: () -> Unit
) {
    Greeting(name = "John Doe", onExit = onExit)
}

@Composable
private fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    onExit: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets.systemBars
    ) { contentPadding ->

        BottomBarNavigationNested(
            navBarItemList = listOf(CameraNested),
            onExit = onExit
        )
    }
}
