package com.macaosoftware.nav3playground.moduleA.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.macaosoftware.nav3playground.common.ui.view.ContentRed

@Composable
fun FeedContainer(
    callback: FeedContainerCallback,
    modifier: Modifier = Modifier
) {
    ContentRed(title = "Unauthenticated Feed Screen") {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Please Login to be able to see the Feed content",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(height = 56.dp))
            Button(onClick = {
                callback.goToAuthScreen.invoke()
            }) {
                Text("Go to Auth")
            }
        }
    }
}

class FeedContainerCallback(
    val goToAuthScreen: () -> Unit
)
