package com.macaosoftware.nav3playground.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.macaosoftware.nav3playground.common.search.arch.SearchNav3Graph
import com.macaosoftware.nav3playground.common.ui.navigation.LocalResultStore
import com.macaosoftware.nav3playground.common.ui.navigation.ResultStore
import com.macaosoftware.nav3playground.moduleA.arch.FeedNav3Graph
import com.macaosoftware.nav3playground.moduleA.arch.ModuleANav3Graph
import com.macaosoftware.nav3playground.moduleB.arch.ModuleBNav3Graph
import com.macaosoftware.nav3playground.startup.Nav3PlaygroundApplication
import com.macaosoftware.nav3playground.ui.view.HomeContainer
import dev.zacsweers.metro.Inject

class HomeActivity : ComponentActivity() {

    @Inject
    private lateinit var feedFeatureModule: FeedNav3Graph

    @Inject
    private lateinit var featureAModule: ModuleANav3Graph

    @Inject
    private lateinit var featureBModule: ModuleBNav3Graph

    @Inject
    private lateinit var searchFeatureModule: SearchNav3Graph

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
            searchFeatureModule,
            feedFeatureModule,
            featureBModule,
            featureAModule
        )

        // Add the 3 modules we want to show in the BottomNavigationBar
        val navBarItemList = listOf(
            feedFeatureModule.entryPointNavBarItem(),
            featureAModule.entryPointNavBarItem(),
            featureBModule.entryPointNavBarItem()
        )

        setContent {
            val resultStore = remember { ResultStore() }
            CompositionLocalProvider(value = LocalResultStore.provides(resultStore)) {
                HomeContainer(
                    nav3GraphList = featureModuleList,
                    navBarItemList = navBarItemList,
                    onExit = { finish() }
                )
            }
        }
    }
}
