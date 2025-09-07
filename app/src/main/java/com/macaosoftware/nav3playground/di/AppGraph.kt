package com.macaosoftware.nav3playground.di

import com.macaosoftware.nav3playground.startup.Initializer
import com.macaosoftware.nav3playground.startup.MainActivityCoordinator
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.ElementsIntoSet
import dev.zacsweers.metro.Provides

@DependencyGraph
interface AppGraph {

    val mainActivityCoordinator: MainActivityCoordinator
    val initializers: Set<Initializer>
    val initializerA: InitializerA
    val initializerB: InitializerB
    val initializerFeed: InitializerFeed

    @Provides
    @ElementsIntoSet
    fun provideInitializers(): Set<Initializer> {
        return setOf<Initializer>(initializerA, initializerB, initializerFeed)
    }

    @DependencyGraph.Factory
    fun interface Factory {
        fun create(): AppGraph
    }
}
