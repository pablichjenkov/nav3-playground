package com.macaosoftware.nav3playground

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.macaosoftware.nav3playground.common.ContentRed
import com.macaosoftware.nav3playground.common.NavBarItem
import com.macaosoftware.nav3playground.common.Route
import com.macaosoftware.nav3playground.common.StackNavigator

private typealias EntryProviderBuilderLambda = EntryProviderBuilder<Route>.() -> Unit

data object Home : NavBarItem(icon = Icons.Default.Home, description = "Home")

fun getModuleAppEntryProviderBuilder(
    stackNavigator: StackNavigator
): EntryProviderBuilderLambda = {
    entry<Home> {
        ContentRed("Home screen")
    }
}
