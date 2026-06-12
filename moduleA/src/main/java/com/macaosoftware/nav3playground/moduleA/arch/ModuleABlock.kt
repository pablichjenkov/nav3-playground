package com.macaosoftware.nav3playground.moduleA.arch

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.macaosoftware.nav3playground.common.nav3.Nav3Block
import com.macaosoftware.nav3playground.common.nav3.Nav3BlockBuilder
import com.macaosoftware.nav3playground.common.nav3.NavBarItem
import com.macaosoftware.nav3playground.common.nav3.SingleStackNavigator
import com.macaosoftware.nav3playground.common.results.ResultA
import com.macaosoftware.nav3playground.common.results.ResultFeed
import com.macaosoftware.nav3playground.common.ui.view.ContentGreen
import com.macaosoftware.nav3playground.moduleA.ui.ChatDetail
import com.macaosoftware.nav3playground.moduleA.ui.ChatList
import com.macaosoftware.nav3playground.moduleA.ui.RouteAFinal
import com.macaosoftware.nav3playground.moduleA.ui.view.ChatDetailScreen
import com.macaosoftware.nav3playground.moduleA.ui.view.ScreenA
import dev.zacsweers.metro.Inject

@Inject
class ModuleABlock(
    moduleABlockGraphFactory: ModuleABlockGraph.Factory
) : Nav3Block<ModuleABlock.Input> {
    val moduleANodeGraph = moduleABlockGraphFactory.createModuleANodeGraph()
    var screenAViewModel = moduleANodeGraph.screenAViewModel
    var chatDetailScreenViewModel = moduleANodeGraph.chatDetailScreenViewModel

    override fun entryPointNavBarItem(): NavBarItem = ChatList

    context(entryProviderScope: EntryProviderScope<NavKey>)
    override fun installEntries(
        input: Input
    ): Nav3BlockBuilder = Nav3BlockBuilder(entryProviderScope) {
        entry<ChatList> {
            ContentGreen("Chat list screen") {
                Button(onClick = {
                    input.singleStackNavigator.navigate(navKey = ChatDetail)
                }) {
                    Text("Go to conversation")
                }
            }
        }
        entry<ChatDetail> {
            ChatDetailScreen(
                chatDetailScreenViewModel = chatDetailScreenViewModel,
                onClick = {
                    input.singleStackNavigator.navigate(navKey = RouteAFinal)
                }
            )
        }
        entry<RouteAFinal> {
            ScreenA(
                screenAViewModel = screenAViewModel,
                onResult = input.onResult
            )
        }
    }

    class Input(
        val singleStackNavigator: SingleStackNavigator,
        val onResult: (ResultA) -> Unit
    )
}
