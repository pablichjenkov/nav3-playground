package com.macaosoftware.nav3playground.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.macaosoftware.nav3playground.arch.FeatureAppModule
import com.macaosoftware.nav3playground.common.arch.CommonFeatureModule
import com.macaosoftware.nav3playground.moduleA.arch.FeatureAModule
import com.macaosoftware.nav3playground.moduleB.arch.FeatureBModule
import com.macaosoftware.nav3playground.ui.view.HomeContainer

class HomeActivity : ComponentActivity() {

    // @Inject
    private val featureAModule = FeatureAModule()

    // @Inject
    private val featureBModule = FeatureBModule()

    // @Inject
    private val commonFeatureModule = CommonFeatureModule()

    // @Inject
    private val featureAppModule = FeatureAppModule()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Add all modules we want to be able to render
        val featureModuleList = listOf(
            commonFeatureModule,
            featureBModule,
            featureAModule,
            featureAppModule
        )

        // Add the 3 modules we want to show in the BottomNavigationBar
        val navBarItemList = listOf(
            featureAppModule.getModuleNavBarItem(),
            featureAModule.getModuleNavBarItem(),
            featureBModule.getModuleNavBarItem()
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
