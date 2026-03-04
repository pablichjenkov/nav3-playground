package com.macaosoftware.nav3playground.moduleA.arch

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation3.runtime.EntryProviderScope
import com.macaosoftware.nav3playground.common.arch.ResultA
import com.macaosoftware.nav3playground.common.ui.navigation.Nav3Block
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.Route
import com.macaosoftware.nav3playground.common.ui.navigation.SingleStackNavigator
import com.macaosoftware.nav3playground.common.ui.view.ContentGreen
import com.macaosoftware.nav3playground.moduleA.ui.view.ChatDetailScreen
import com.macaosoftware.nav3playground.moduleA.ui.view.ScreenA
import dev.zacsweers.metro.Inject

@Inject
class ModuleABlock(
    moduleANodeGraphFactory: ModuleANodeGraph.Factory
) : Nav3Block {
    val moduleANodeGraph = moduleANodeGraphFactory.createModuleANodeGraph()
    var screenAViewModel = moduleANodeGraph.screenAViewModel
    var chatDetailScreenViewModel = moduleANodeGraph.chatDetailScreenViewModel

    override fun entryPointNavBarItem(): NavBarItem = ChatList

    fun EntryProviderScope<Route>.install(
        singleStackNavigator: SingleStackNavigator,
        onResult: (ResultA) -> Unit
    ) {
        entry<ChatList> {
            ContentGreen("Chat list screen") {
                Button(onClick = {
                    singleStackNavigator.navigate(route = ChatDetail)
                }) {
                    Text("Go to conversation")
                }
            }
        }
        entry<ChatDetail> {
            ChatDetailScreen(
                chatDetailScreenViewModel = chatDetailScreenViewModel,
                onClick = {
                    singleStackNavigator.navigate(route = RouteAFinal)
                }
            )
        }
        entry<RouteAFinal> {
            ScreenA(
                screenAViewModel = screenAViewModel,
                onResult = onResult
            )
        }
    }
}
