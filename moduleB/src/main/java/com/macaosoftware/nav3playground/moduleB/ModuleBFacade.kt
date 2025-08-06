package com.macaosoftware.nav3playground.moduleB

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.macaosoftware.nav3playground.common.ContentPurple
import com.macaosoftware.nav3playground.common.NavBarItem
import com.macaosoftware.nav3playground.common.Route
import com.macaosoftware.nav3playground.common.StackNavigator
import kotlinx.serialization.Serializable

private typealias EntryProviderBuilderLambda = EntryProviderBuilder<Route>.() -> Unit

private data object Camera : NavBarItem(icon = Icons.Default.PlayArrow, description = "Camera")

internal data object CameraNested : NavBarItem(icon = Icons.Default.PlayArrow, description = "Camera Nested")

@Serializable
private data class RouteBFinal(val id: String) : Route()

fun getModuleBNavBarItem(): NavBarItem = Camera

fun getModuleBEntryProviderBuilder(
    stackNavigator: StackNavigator
): EntryProviderBuilderLambda = {
    entry<Camera> {
        ContentPurple("Module B") {
            Button(onClick = {
                stackNavigator.navigateInsideCurrentTopLevel(
                    navBarItem = Camera,
                    route = RouteBFinal(id = "12345")
                )
            }) {
                Text("Go to nested nav-display")
            }
        }
    }
    entry<RouteBFinal> {
        ScreensB { stackNavigator.goBack {  } }
    }
}
