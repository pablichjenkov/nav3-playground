package com.macaosoftware.nav3playground.moduleA.ui.view

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.macaosoftware.nav3playground.common.ui.view.ContentBlue

@Composable
internal fun ChatDetailScreen(
    modifier: Modifier = Modifier,
    chatDetailScreenViewModel: ChatDetailScreenViewModel,
    onClick: () -> Unit,
) {
    ContentBlue("Chat Detail Screen") {
        Button(onClick = onClick) {
            Text("Go to last screen")
        }
    }
}