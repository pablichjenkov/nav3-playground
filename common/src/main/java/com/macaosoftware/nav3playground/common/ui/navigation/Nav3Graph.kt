package com.macaosoftware.nav3playground.common.ui.navigation

import androidx.navigation3.runtime.EntryProviderScope

typealias EntryProviderScopeLambda = EntryProviderScope<Route>.() -> Unit

interface Nav3Graph {

    fun entryPointNavBarItem(): NavBarItem
}