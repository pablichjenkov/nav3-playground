package com.macaosoftware.nav3playground.di

import com.macaosoftware.nav3playground.moduleA.arch.ModuleABlockGraph
import com.macaosoftware.nav3playground.moduleB.arch.ModuleBBlockGraph
import com.macaosoftware.nav3playground.startup.Initializer
import com.macaosoftware.nav3playground.startup.MainActivityCoordinator
import com.macaosoftware.nav3playground.ui.activity.HomeActivity
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.ElementsIntoSet
import dev.zacsweers.metro.Provides

@DependencyGraph(scope = AppScope::class)
interface AppGraph {

    val mainActivityCoordinator: MainActivityCoordinator

    val initializerA: InitializerA
    val initializerB: InitializerB
    val initializerFeed: InitializerFeed
    val initializers: Set<Initializer>

    fun inject(target: HomeActivity)

    @Provides
    @ElementsIntoSet
    fun provideInitializers(): Set<Initializer> {
        return setOf<Initializer>(initializerA, initializerB, initializerFeed)
    }

    /**** Children Graph factories has to be exposed. ****/
    val moduleABlockGraphFactory: ModuleABlockGraph.Factory
    val moduleBBlockGraphFactory: ModuleBBlockGraph.Factory

    @DependencyGraph.Factory
    fun interface Factory {
        fun create(): AppGraph
    }
}
