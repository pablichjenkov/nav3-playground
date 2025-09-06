package com.macaosoftware.nav3playground.startup

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

private typealias OnReadyCallBack = () -> Unit

interface AppStartupManager {
    fun applicationStart()
    fun onReady(onReadyCallBack: OnReadyCallBack)
}

object AppStartupManagerImpl : AppStartupManager {

    var allDependenciesReady = false
    val onReadyWaitingList: MutableList<OnReadyCallBack> = mutableListOf()
    // val rootDependencyGraph

    @OptIn(DelicateCoroutinesApi::class)
    override fun applicationStart() {
        GlobalScope.launch(context = Dispatchers.Default) {
            delay(duration = 2.seconds)

            // Inject Initializers from Dagger here and run their execution
            // val initializers = rootDependencyGraph.getInitializers()
            // initializers.forEach { it.initialize() }

            allDependenciesReady = true
            onReadyWaitingList.forEach { it.invoke() }
            onReadyWaitingList.clear()
        }
    }

    override fun onReady(onReadyCallBack: OnReadyCallBack) {
        if (allDependenciesReady) {
            onReadyCallBack.invoke()
            return
        }
        onReadyWaitingList.add(onReadyCallBack)
    }

}
