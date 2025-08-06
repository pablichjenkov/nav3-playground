package com.macaosoftware.nav3playground.moduleA

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.macaosoftware.nav3playground.common.ContentBlue
import com.macaosoftware.nav3playground.common.ContentGreen
import com.macaosoftware.nav3playground.common.NavBarItem
import com.macaosoftware.nav3playground.common.Route
import com.macaosoftware.nav3playground.common.StackNavigator
import kotlinx.serialization.Serializable

private typealias EntryProviderBuilderLambda = EntryProviderBuilder<Route>.() -> Unit

private object ChatList : NavBarItem(icon = Icons.Default.Face, description = "Chat list")
private data object ChatDetail : Route()

@Serializable
internal data object RouteAFinal : Route()

fun getModuleANavBarItem(): NavBarItem = ChatList

fun getModuleAEntryProviderBuilder(
    stackNavigator: StackNavigator,
    onModuleAResult: () -> Unit
): EntryProviderBuilderLambda = {
    entry<ChatList> {
        ContentGreen("Chat list screen") {
            Button(onClick = {
                stackNavigator.navigateInsideCurrentTopLevel(
                    navBarItem = ChatList,
                    route = ChatDetail
                )
            }) {
                Text("Go to conversation")
            }
        }
    }
    entry<ChatDetail> {
        ContentBlue("Chat Detail Screen") {
            Button(onClick = {
                stackNavigator.navigateInsideCurrentTopLevel(
                    navBarItem = ChatList,
                    route = RouteAFinal
                )
            }) {
                Text("Go to last screen")
            }
        }
    }
    entry<RouteAFinal> {
        ScreenA(
            onResult = onModuleAResult
        )
    }
}
