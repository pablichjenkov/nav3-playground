package com.macaosoftware.nav3playground.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.macaosoftware.nav3playground.common.search.arch.SearchFeatureModule
import com.macaosoftware.nav3playground.moduleA.arch.FeatureAModule
import com.macaosoftware.nav3playground.moduleA.arch.FeedFeatureModule
import com.macaosoftware.nav3playground.moduleB.arch.FeatureBModule
import com.macaosoftware.nav3playground.ui.view.HomeContainer

class HomeActivity : ComponentActivity() {

    // @Inject
    private val feedFeatureModule = FeedFeatureModule()

    // @Inject
    private val featureAModule = FeatureAModule()

    // @Inject
    private val featureBModule = FeatureBModule()

    // @Inject
    private val searchFeatureModule = SearchFeatureModule()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Add all modules we want to be able to render
        val featureModuleList = listOf(
            searchFeatureModule,
            feedFeatureModule,
            featureBModule,
            featureAModule
        )

        // Add the 3 modules we want to show in the BottomNavigationBar
        val navBarItemList = listOf(
            feedFeatureModule.getEntryPointNavBarItem(),
            featureAModule.getEntryPointNavBarItem(),
            featureBModule.getEntryPointNavBarItem()
        )

        setContent {
            HomeContainer(
                featureModuleList = featureModuleList,
                navBarItemList = navBarItemList,
                onExit = { finish() }
            )
        }
    }
}
