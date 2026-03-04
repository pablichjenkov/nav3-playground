package com.macaosoftware.nav3playground.common.ui.navigation

interface SingleStackNavigator {

    val childrenTopLevelNavigatorMap: MutableMap<String, TopLevelNavigator>

    fun navigate(
        route: Route,
        navigationMode: NavigationMode = NavigationMode.NewInstance
    )

    fun goBack()
}