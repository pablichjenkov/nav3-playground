package com.macaosoftware.nav3playground.moduleA.ui.view

import com.macaosoftware.nav3playground.moduleA.arch.SharedDataManager
import dev.zacsweers.metro.Inject

@Inject
class ChatDetailScreenViewModel(
    sharedDataManager: SharedDataManager
) {
    init {
        println("Pablo, ChatDetailScreenViewModel::sharedDataManager instance = ${sharedDataManager.random}")
    }
}