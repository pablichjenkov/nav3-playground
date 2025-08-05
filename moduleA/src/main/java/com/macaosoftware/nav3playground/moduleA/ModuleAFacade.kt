package com.macaosoftware.nav3playground.moduleA

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.macaosoftware.nav3playground.common.ContentBlue
import com.macaosoftware.nav3playground.common.ContentGreen
import com.macaosoftware.nav3playground.common.NavBarItem
import com.macaosoftware.nav3playground.common.Route
import com.macaosoftware.nav3playground.common.StackNavigator

private typealias EntryProviderBuilderLambda = EntryProviderBuilder<Route>.() -> Unit

data object ChatList : NavBarItem(icon = Icons.Default.Face, description = "Chat list")
private data object ChatDetail : Route()

fun getModuleAEntryPoint(): NavKey = RouteAMain

fun getModuleAEntryProviderBuilder(
    stackNavigator: StackNavigator,
    onModuleAResult: () -> Unit
): EntryProviderBuilderLambda = {
    entry<ChatList> {
        ContentGreen("Chat list screen") {
            Button(onClick = {
                stackNavigator.navigateInsideCurrentTopLevel(
                    ChatList,
                    ChatDetail
                )
            }) {
                Text("Go to conversation")
            }
        }
    }
    entry<ChatDetail> {
        ContentBlue("Chat detail screen")
    }
    /*entry<RouteAMain> {
        ScreenA(
            onNextClick = { backStack.add(RouteAInternal1("123")) },
            onResult = onModuleAResult
        )
    }
    entry<RouteAInternal1> {
        ScreenAInternal1(
            onDoneClick = { backStack.removeLastOrNull() }
        )
    }*/
}
