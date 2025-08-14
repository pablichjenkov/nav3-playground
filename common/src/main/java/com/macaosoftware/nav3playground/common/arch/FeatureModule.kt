package com.macaosoftware.nav3playground.common.arch

import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem

interface FeatureModule {

    fun getEntryPointNavBarItem(): NavBarItem
}