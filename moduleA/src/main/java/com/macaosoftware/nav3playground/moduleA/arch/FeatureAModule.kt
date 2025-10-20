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
import com.macaosoftware.nav3playground.common.ui.navigation.SingleStackNavigator
import com.macaosoftware.nav3playground.common.ui.view.ContentBlue
import com.macaosoftware.nav3playground.common.ui.view.ContentGreen
import com.macaosoftware.nav3playground.moduleA.ui.view.ScreenA
import dev.zacsweers.metro.Inject
import kotlinx.serialization.Serializable

private typealias EntryProviderBuilderLambda = EntryProviderBuilder<Route>.() -> Unit

private object ChatList : NavBarItem(icon = Icons.Default.Face, description = "Chat list")
private data object ChatDetail : Route

@Serializable
private data object RouteAFinal : Route

@Inject
class FeatureAModule : FeatureModule {

    override fun getEntryPointNavBarItem(): NavBarItem = ChatList

    fun getModuleAEntryProviderBuilder(
        singleStackNavigator: SingleStackNavigator,
        onResult: () -> Unit
    ): EntryProviderBuilderLambda = {
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
