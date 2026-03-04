package com.macaosoftware.nav3playground.moduleA.ui.view

import com.macaosoftware.nav3playground.moduleA.arch.SharedDataManager
import dev.zacsweers.metro.Inject

@Inject
class ScreenAViewModel(
    sharedDataManager: SharedDataManager
) {
    init {
        println("Pablo, ScreenAViewModel::sharedDataManager instance = ${sharedDataManager.random}")
    }
}