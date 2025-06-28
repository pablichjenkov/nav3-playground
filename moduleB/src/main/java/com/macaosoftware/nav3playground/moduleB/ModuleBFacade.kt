package com.macaosoftware.nav3playground.moduleB

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry

typealias EntryProviderBuilderLambda = EntryProviderBuilder<NavKey>.() -> Unit

fun getModuleBEntryPoint(): NavKey = RouteBMain

fun getModuleBEntryProviderBuilder(backStack: NavBackStack): EntryProviderBuilderLambda = {
    entry<RouteBMain> {
        ScreensB()
    }
}