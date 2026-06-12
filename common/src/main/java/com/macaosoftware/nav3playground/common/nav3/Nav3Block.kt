package com.macaosoftware.nav3playground.common.nav3

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey

typealias EntryProviderBuilder = EntryProviderScope<NavKey>.() -> Unit

class Nav3BlockBuilder(
    entryProviderScope: EntryProviderScope<NavKey>,
    entryProviderBuilder: EntryProviderBuilder
) {

    init {
        entryProviderBuilder.invoke(entryProviderScope)
    }
}

interface Nav3Block<Input> {

    fun entryPointNavBarItem(): NavBarItem

    context(entryProviderScope: EntryProviderScope<NavKey>)
    fun installEntries(
        input: Input
    ): Nav3BlockBuilder
}
