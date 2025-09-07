package com.macaosoftware.nav3playground.startup

import com.macaosoftware.nav3playground.di.AppGraph
import dev.zacsweers.metro.createGraphFactory
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

private typealias OnReadyCallBack = (AppGraph) -> Unit

interface AppStartupManager {
    fun applicationStart()
    fun onReady(onReadyCallBack: OnReadyCallBack)
}

object AppStartupManagerImpl : AppStartupManager {

    private var allDependenciesReady = false
    private val onReadyWaitingList: MutableList<OnReadyCallBack> = mutableListOf()
    private lateinit var appGraph: AppGraph

    @OptIn(DelicateCoroutinesApi::class)
    override fun applicationStart() {
        GlobalScope.launch(context = Dispatchers.Default) {
            delay(duration = 2.seconds)
            appGraph = createGraphFactory<AppGraph.Factory>().create()

            // Run all App initializers
            appGraph.initializers.forEach {
                it.initialize()
            }

            allDependenciesReady = true
            onReadyWaitingList.forEach { it.invoke(appGraph) }
            onReadyWaitingList.clear()
        }
    }

    override fun onReady(onReadyCallBack: OnReadyCallBack) {
        if (allDependenciesReady) {
            onReadyCallBack.invoke(appGraph)
            return
        }
        onReadyWaitingList.add(onReadyCallBack)
    }

}
