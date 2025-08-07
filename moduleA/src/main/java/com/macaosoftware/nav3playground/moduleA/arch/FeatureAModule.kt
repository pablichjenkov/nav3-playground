package com.macaosoftware.nav3playground.moduleA.arch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.macaosoftware.nav3playground.common.arch.FeatureModule
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.Route
import com.macaosoftware.nav3playground.common.ui.navigation.StackNavigator
import com.macaosoftware.nav3playground.common.ui.view.ContentBlue
import com.macaosoftware.nav3playground.common.ui.view.ContentGreen
import com.macaosoftware.nav3playground.moduleA.ui.view.ScreenA
import kotlinx.serialization.Serializable

private typealias EntryProviderBuilderLambda = EntryProviderBuilder<Route>.() -> Unit

private object ChatList : NavBarItem(icon = Icons.Default.Face, description = "Chat list")
private data object ChatDetail : Route

@Serializable
private data object RouteAFinal : Route

class FeatureAModule : FeatureModule {

    override fun getModuleNavBarItem(): NavBarItem = ChatList

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

}
