package com.macaosoftware.nav3playground.moduleB.ui.view

import com.macaosoftware.nav3playground.moduleB.arch.ModuleBSharedDataManager
import dev.zacsweers.metro.Inject

@Inject
class ScreenB0ViewModel(
    moduleBSharedDataManager: ModuleBSharedDataManager
) {
    init {
        println("Pablo, ScreenAViewModel::sharedDataManager instance = ${moduleBSharedDataManager.random}")
    }
}