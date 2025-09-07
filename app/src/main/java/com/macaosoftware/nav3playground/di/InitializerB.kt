package com.macaosoftware.nav3playground.di

import android.util.Log
import com.macaosoftware.nav3playground.startup.Initializer
import dev.zacsweers.metro.ClassKey
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.IntoSet

@Inject
@IntoSet
@ClassKey(value = Initializer::class)
class InitializerB(
    initializerFeed: InitializerFeed
) : Initializer {
    override suspend fun initialize() {
        Log.d("InitializerB", "InitializerB::initialize()")
    }
}