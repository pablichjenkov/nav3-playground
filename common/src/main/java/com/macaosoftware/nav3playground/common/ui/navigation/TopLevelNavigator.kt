package com.macaosoftware.nav3playground.common.ui.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList

class TopLevelNavigator(
    navBarItemList: List<NavBarItem>,
    val onExit: () -> Unit
) {

    private val stackNavigator = StackNavigator(
        navBarItemList = navBarItemList,
        onExit = onExit
    )

    val backStack: SnapshotStateList<Route>
        get() = stackNavigator.backStack

    val currentNavItem: NavBarItem
        get() = stackNavigator.currentNavItem

    fun selectTopLevel(navBarItem: NavBarItem) = stackNavigator.selectTopLevel(navBarItem)

    fun pushRouteIntoCurrentTopLevel(
        route: Route,
        navigationMode: NavigationMode = NavigationMode.NewInstance
    ) = stackNavigator.pushRouteIntoCurrentTopLevel(route = route, navigationMode = navigationMode)

    fun goBack() = stackNavigator.goBack()

    fun getSingleStackNavigator(navBarItem: NavBarItem): SingleStackNavigator {
        return SingleStackNavigatorImpl(
            stackNavigator = stackNavigator,
            navBarItem = navBarItem
        )
    }
}
