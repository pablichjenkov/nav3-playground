package com.macaosoftware.nav3playground.moduleB.arch

import com.macaosoftware.nav3playground.moduleB.ui.view.ScreenB0ViewModel
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.GraphExtension

@GraphExtension(ModuleBBlockScope::class)
interface ModuleBBlockGraph {

    val screenB0ViewModel: ScreenB0ViewModel

    @GraphExtension.Factory
    @ContributesTo(AppScope::class)
    interface Factory {
        fun createModuleBNodeGraph(): ModuleBBlockGraph
    }
}