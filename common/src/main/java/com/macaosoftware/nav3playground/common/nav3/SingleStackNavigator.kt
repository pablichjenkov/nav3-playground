package com.macaosoftware.nav3playground.common.nav3

import androidx.navigation3.runtime.NavKey

interface SingleStackNavigator {

    val childrenTopLevelNavigatorMap: MutableMap<String, TopLevelNavigator>

    fun navigate(
        navKey: NavKey,
        navigationMode: NavigationMode = NavigationMode.NewInstance
    )

    fun goBack()
}