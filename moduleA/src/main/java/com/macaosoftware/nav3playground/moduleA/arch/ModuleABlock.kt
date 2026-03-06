package com.macaosoftware.nav3playground.moduleA.arch

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.macaosoftware.nav3playground.common.results.ResultA
import com.macaosoftware.nav3playground.common.nav3.Nav3Block
import com.macaosoftware.nav3playground.common.nav3.NavBarItem
import com.macaosoftware.nav3playground.common.nav3.SingleStackNavigator
import com.macaosoftware.nav3playground.common.ui.view.ContentGreen
import com.macaosoftware.nav3playground.moduleA.ui.view.ChatDetailScreen
import com.macaosoftware.nav3playground.moduleA.ui.view.ScreenA
import dev.zacsweers.metro.Inject

@Inject
class ModuleABlock(
    moduleABlockGraphFactory: ModuleABlockGraph.Factory
) : Nav3Block {
    val moduleANodeGraph = moduleABlockGraphFactory.createModuleANodeGraph()
    var screenAViewModel = moduleANodeGraph.screenAViewModel
    var chatDetailScreenViewModel = moduleANodeGraph.chatDetailScreenViewModel

    override fun entryPointNavBarItem(): NavBarItem = ChatList

    fun EntryProviderScope<NavKey>.install(
        singleStackNavigator: SingleStackNavigator,
        onResult: (ResultA) -> Unit
    ) {
        entry<ChatList> {
            ContentGreen("Chat list screen") {
                Button(onClick = {
                    singleStackNavigator.navigate(navKey = ChatDetail)
                }) {
                    Text("Go to conversation")
                }
            }
        }
        entry<ChatDetail> {
            ChatDetailScreen(
                chatDetailScreenViewModel = chatDetailScreenViewModel,
                onClick = {
                    singleStackNavigator.navigate(navKey = RouteAFinal)
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
