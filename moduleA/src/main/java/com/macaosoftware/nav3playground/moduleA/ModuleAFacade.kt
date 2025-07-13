package com.macaosoftware.nav3playground.moduleA

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry

typealias EntryProviderBuilderLambda = EntryProviderBuilder<NavKey>.() -> Unit

fun getModuleAEntryPoint(): NavKey = RouteAMain

fun getModuleAEntryProviderBuilder(
    backStack: NavBackStack,
    onModuleAResult: () -> Unit
): EntryProviderBuilderLambda = {
    entry<RouteAMain> {
        ScreensA(
            onNextClick = { backStack.add(RouteAInternal1("123")) },
            onResult = onModuleAResult
        )
    }
    entry<RouteAInternal1> {
        ScreenAInternal1(
            onDoneClick = { backStack.removeLastOrNull() }
        )
    }
}
