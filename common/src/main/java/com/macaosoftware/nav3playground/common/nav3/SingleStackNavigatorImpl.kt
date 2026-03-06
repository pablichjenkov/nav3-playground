package com.macaosoftware.nav3playground.common.nav3

import androidx.navigation3.runtime.NavKey

internal class SingleStackNavigatorImpl(
    private val stackNavigator: StackNavigator,
    private val navBarItem: NavBarItem
) : SingleStackNavigator {

    override val childrenTopLevelNavigatorMap: MutableMap<String, TopLevelNavigator>
        get() = stackNavigator.childrenTopLevelNavigatorMap

    override fun navigate(
        navKey: NavKey,
        navigationMode: NavigationMode
    ) {
        stackNavigator.navigate(
            navBarItem = navBarItem,
            navKey = navKey,
            navigationMode = navigationMode
        )
    }

    override fun goBack() {
        stackNavigator.goBack()
    }
}
