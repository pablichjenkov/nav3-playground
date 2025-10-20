package com.macaosoftware.nav3playground.common.ui.navigation

interface SingleStackNavigator {

    val childrenStackNavigatorMap: MutableMap<String, TopLevelNavigator>

    fun navigate(
        route: Route,
        navigationMode: NavigationMode = NavigationMode.NewInstance
    )

    fun goBack()
}