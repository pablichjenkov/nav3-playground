package com.macaosoftware.nav3playground.arch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.macaosoftware.nav3playground.common.arch.FeatureModule
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.Route
import com.macaosoftware.nav3playground.common.ui.navigation.StackNavigator
import com.macaosoftware.nav3playground.common.ui.view.ContentRed

private typealias EntryProviderBuilderLambda = EntryProviderBuilder<Route>.() -> Unit

private data object Home : NavBarItem(icon = Icons.Default.Home, description = "Home")

class FeatureAppModule : FeatureModule {

    override fun getModuleNavBarItem(): NavBarItem = Home

    fun getModuleAppEntryProviderBuilder(
        stackNavigator: StackNavigator
    ): EntryProviderBuilderLambda = {
        entry<Home> {
            ContentRed("Home screen")
        }
    }
}