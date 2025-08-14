package com.macaosoftware.nav3playground.moduleB.arch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.macaosoftware.nav3playground.common.arch.FeatureModule
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.Route
import com.macaosoftware.nav3playground.common.ui.navigation.StackNavigator
import com.macaosoftware.nav3playground.moduleB.ui.ModuleBDrawerNavigation

private typealias EntryProviderBuilderLambda = EntryProviderBuilder<Route>.() -> Unit

private data object Camera : NavBarItem(
    icon = Icons.Default.Call,
    description = "Camera"
)

internal data object PageB0NavItem : NavBarItem(
    icon = Icons.Default.PlayArrow,
    description = "Page B - 0 Nested Display"
)

internal data object PageB1NavItem : NavBarItem(
    icon = Icons.Default.AddCircle,
    description = "Page B - 1 Nested Display"
)

internal data object PageB2NavItem : NavBarItem(
    icon = Icons.Default.Face,
    description = "Page B - 2 Nested Display"
)

// @Serializable
// private data class RouteBFinal(val id: String) : Route

class FeatureBModule : FeatureModule {

    override fun getEntryPointNavBarItem(): NavBarItem = Camera

    fun getModuleBEntryProviderBuilder(
        stackNavigator: StackNavigator,
        onResult: () -> Unit
    ): EntryProviderBuilderLambda = {
        entry<Camera> {

            val navBarItemList = remember {
                listOf(PageB0NavItem, PageB1NavItem, PageB2NavItem)
            }

            ModuleBDrawerNavigation(
                parentStackNavigator = stackNavigator,
                navBarItemList = navBarItemList,
                onExit = { stackNavigator.goBack() }
            )
        }
    }

}
