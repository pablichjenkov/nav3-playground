package com.macaosoftware.nav3playground.common.nav3

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey

class TopLevelNavigator(
    navBarItemList: List<NavBarItem>,
    val onExit: () -> Unit
) {

    private val stackNavigator = StackNavigator(
        navBarItemList = navBarItemList,
        onExit = onExit
    )

    val backStack: SnapshotStateList<NavKey>
        get() = stackNavigator.backStack

    val currentNavItem: NavBarItem
        get() = stackNavigator.currentNavItem

    fun selectTopLevel(navBarItem: NavBarItem) = stackNavigator.selectTopLevel(navBarItem)

    fun pushRouteIntoCurrentTopLevel(
        navKey: NavKey,
        navigationMode: NavigationMode = NavigationMode.NewInstance
    ) = stackNavigator.pushRouteIntoCurrentTopLevel(navKey = navKey, navigationMode = navigationMode)

    fun goBack() = stackNavigator.goBack()

    fun getSingleStackNavigator(navBarItem: NavBarItem): SingleStackNavigator {
        return SingleStackNavigatorImpl(
            stackNavigator = stackNavigator,
            navBarItem = navBarItem
        )
    }
}
