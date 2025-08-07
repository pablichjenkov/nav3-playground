package com.macaosoftware.nav3playground.moduleB.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun ScreenB0(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets.systemBars
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Cyan)
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Module B Page 0",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}


@Composable
internal fun ScreenB1(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets.systemBars
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Magenta)
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Module B Page 1",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
internal fun ScreenB2(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets.systemBars
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Module B Page 2",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
