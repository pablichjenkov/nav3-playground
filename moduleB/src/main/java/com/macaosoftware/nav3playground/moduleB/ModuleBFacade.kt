package com.macaosoftware.nav3playground.moduleB

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.macaosoftware.nav3playground.common.ContentPink
import com.macaosoftware.nav3playground.common.ContentPurple
import com.macaosoftware.nav3playground.common.NavBarItem
import com.macaosoftware.nav3playground.common.Route
import com.macaosoftware.nav3playground.common.StackNavigator

private typealias EntryProviderBuilderLambda = EntryProviderBuilder<Route>.() -> Unit

data object Camera : NavBarItem(icon = Icons.Default.PlayArrow, description = "Camera")
private data object Search : Route()

fun getModuleBEntryPoint(): NavKey = RouteBMain

fun getModuleBEntryProviderBuilder(stackNavigator: StackNavigator): EntryProviderBuilderLambda = {
    entry<Camera> {
        ContentPurple("Camera screen")
    }
    entry<RouteBMain> {
        ScreensB()
    }
}
