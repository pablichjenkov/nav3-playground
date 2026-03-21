package com.macaosoftware.nav3playground.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.macaosoftware.nav3playground.common.nav3.LocalResultStore
import com.macaosoftware.nav3playground.common.nav3.ResultStore
import com.macaosoftware.nav3playground.common.search.arch.SearchBlock
import com.macaosoftware.nav3playground.moduleA.arch.FeedBlock
import com.macaosoftware.nav3playground.moduleA.arch.ModuleABlock
import com.macaosoftware.nav3playground.moduleB.arch.ModuleBBlock
import com.macaosoftware.nav3playground.startup.Nav3PlaygroundApplication
import com.macaosoftware.nav3playground.ui.view.HomeContainer
import dev.zacsweers.metro.Inject

class HomeActivity : ComponentActivity() {

    @Inject
    private lateinit var feedBlock: FeedBlock

    @Inject
    private lateinit var moduleABlock: ModuleABlock

    @Inject
    private lateinit var moduleBBlock: ModuleBBlock

    @Inject
    private lateinit var searchBlock: SearchBlock

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as Nav3PlaygroundApplication).appStartupManager.onReady { graph ->
            graph.inject(target = this@HomeActivity)
            onActivityReady()
        }
    }

    private fun onActivityReady() {
        // Add all modules we want to be able to render
        val featureModuleList = listOf(
            searchBlock,
            feedBlock,
            moduleBBlock,
            moduleABlock
        )

        // Add the 3 modules we want to show in the BottomNavigationBar
        val navBarItemList = listOf(
            feedBlock.entryPointNavBarItem(),
            moduleABlock.entryPointNavBarItem(),
            moduleBBlock.entryPointNavBarItem()
        )

        setContent {
            val resultStore = remember { ResultStore() }
            CompositionLocalProvider(value = LocalResultStore.provides(resultStore)) {
                HomeContainer(
                    nav3BlockList = featureModuleList,
                    navBarItemList = navBarItemList,
                    onExit = { finish() }
                )
            }
        }
    }
}
