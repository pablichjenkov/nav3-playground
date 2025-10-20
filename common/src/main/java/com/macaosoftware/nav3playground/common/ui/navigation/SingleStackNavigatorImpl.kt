package com.macaosoftware.nav3playground.common.ui.navigation

internal class SingleStackNavigatorImpl(
    private val stackNavigator: StackNavigator,
    private val navBarItem: NavBarItem
) : SingleStackNavigator {

    override val childrenStackNavigatorMap: MutableMap<String, TopLevelNavigator>
        get() = stackNavigator.childrenStackNavigatorMap

    override fun navigate(
        route: Route,
        navigationMode: NavigationMode
    ) {
        stackNavigator.navigate(
            navBarItem = navBarItem,
            route = route,
            navigationMode = navigationMode
        )
    }

    override fun goBack() {
        stackNavigator.goBack()
    }
}
