package com.macaosoftware.nav3playground.moduleA.ui.view

import com.macaosoftware.nav3playground.moduleA.arch.ModuleASharedDataManager
import dev.zacsweers.metro.Inject

@Inject
class ScreenAViewModel(
    moduleASharedDataManager: ModuleASharedDataManager
) {
    init {
        println("Pablo, ScreenAViewModel::sharedDataManager instance = ${moduleASharedDataManager.random}")
    }
}