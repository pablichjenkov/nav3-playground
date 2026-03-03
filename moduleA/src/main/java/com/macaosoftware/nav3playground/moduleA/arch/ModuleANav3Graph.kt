package com.macaosoftware.nav3playground.moduleA.arch

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.macaosoftware.nav3playground.common.arch.ResultA
import com.macaosoftware.nav3playground.common.ui.navigation.EntryProviderScopeLambda
import com.macaosoftware.nav3playground.common.ui.navigation.Nav3Graph
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.SingleStackNavigator
import com.macaosoftware.nav3playground.common.ui.view.ContentBlue
import com.macaosoftware.nav3playground.common.ui.view.ContentGreen
import com.macaosoftware.nav3playground.moduleA.ui.view.ScreenA
import dev.zacsweers.metro.Inject

@Inject
class ModuleANav3Graph() : Nav3Graph {

    override fun entryPointNavBarItem(): NavBarItem = ChatList

    fun entryProviderScope(
        singleStackNavigator: SingleStackNavigator,
        onResult: (ResultA) -> Unit
    ): EntryProviderScopeLambda = {
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
            ContentBlue("Chat Detail Screen") {
                Button(onClick = {
                    singleStackNavigator.navigate(route = RouteAFinal)
                }) {
                    Text("Go to last screen")
                }
            }
        }
        entry<RouteAFinal> {
            ScreenA(onResult = onResult)
        }
    }
}
