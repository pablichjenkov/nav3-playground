package com.macaosoftware.nav3playground.moduleA.ui.view

import com.macaosoftware.nav3playground.moduleA.arch.ModuleASharedDataManager
import dev.zacsweers.metro.Inject

@Inject
class ChatDetailScreenViewModel(
    moduleASharedDataManager: ModuleASharedDataManager
) {
    init {
        println("Pablo, ChatDetailScreenViewModel::sharedDataManager instance = ${moduleASharedDataManager.random}")
    }
}