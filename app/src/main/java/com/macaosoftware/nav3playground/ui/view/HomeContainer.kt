package com.macaosoftware.nav3playground.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationEventHandler
import com.macaosoftware.nav3playground.common.arch.FeatureModule
import com.macaosoftware.nav3playground.common.arch.ResultA
import com.macaosoftware.nav3playground.common.search.arch.SearchFeatureModule
import com.macaosoftware.nav3playground.common.search.arch.SearchNavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.LocalResultStore
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.Route
import com.macaosoftware.nav3playground.common.ui.navigation.StackNavigator
import com.macaosoftware.nav3playground.moduleA.arch.FeatureAModule
import com.macaosoftware.nav3playground.moduleA.arch.FeedFeatureModule
import com.macaosoftware.nav3playground.moduleB.arch.FeatureBModule

@Composable
fun HomeContainer(
    featureModuleList: List<FeatureModule>,
    navBarItemList: List<NavBarItem>,
    onExit: () -> Unit
) {

    val stackNavigator = remember {
        StackNavigator(
            navBarItemList = navBarItemList,
            onExit = onExit
        )
    }

    NavigationEventHandler<NavigationEventInfo>(
        currentInfo = NavigationEventInfo.NotProvided,
        isForwardEnabled = false,
        onBackCompleted = {
            Log.d(
                "DrawerNavigation",
                "NavigationEventHandler::onBackCompleted()"
            )
            stackNavigator.goBack()
        }
    )

    Scaffold(
        topBar = {
            TopAppBarWithSearch {
                stackNavigator.navigateInsideCurrentTopLevel(
                    navBarItem = stackNavigator.currentNavItem,
                    route = (SearchNavBarItem as Route)
                )
            }
        },
        bottomBar = {
            NavigationBar {
                navBarItemList.forEach { topLevelRoute ->

                    val isSelected = topLevelRoute == stackNavigator.currentNavItem

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            stackNavigator.navigateToTopLevel(navBarItem = topLevelRoute)
                        },
                        icon = {
                            Icon(
                                imageVector = topLevelRoute.icon,
                                contentDescription = topLevelRoute.description
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavDisplay(
            modifier = Modifier.padding(paddingValues),
            backStack = stackNavigator.backStack,
            onBack = {
                /**
                 * This onBack is called whenever the backstack is greater than 1.
                 * When the stack is 1 item, the NavigationEventHandler is called instead
                 * */
                stackNavigator.goBack()
            },
            entryProvider = entryProvider {
                featureModuleList.forEach { featureModule ->
                    when (featureModule) {

                        // Add Module Common Routes
                        is SearchFeatureModule -> {
                            featureModule.getModuleCommonEntryProviderBuilder(
                                stackNavigator = stackNavigator,
                                onResult = {}
                            ).invoke(this)
                        }

                        // Add Module A Routes
                        is FeatureAModule -> {
                            val localResultStore = LocalResultStore.current
                            featureModule.getModuleAEntryProviderBuilder(
                                stackNavigator = stackNavigator,
                                onResult = {
                                    // Set a Result for the coming screen to be resumed
                                    localResultStore.setResult<ResultA>(
                                        result = ResultA(
                                            data = "FeatureA_Result_Success"
                                        )
                                    )

                                    // Programmatically navigate to module A to B
                                    stackNavigator.navigateToTopLevel(
                                        navBarItem = navBarItemList[2]
                                    )
                                }

                            ).invoke(this)
                        }

                        // Add Module B Routes
                        is FeatureBModule -> {
                            featureModule.getModuleBEntryProviderBuilder(
                                stackNavigator = stackNavigator,
                                onResult = {}
                            ).invoke(this)
                        }

                        // Add Module Feed Routes
                        is FeedFeatureModule -> {
                            featureModule.getModuleFeedEntryProviderBuilder(
                                stackNavigator = stackNavigator,
                                onResult = {}
                            ).invoke(this)
                        }

                        else -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    modifier = Modifier.wrapContentSize(),
                                    text = "No Composable defined for: $featureModule"
                                )
                            }
                        }
                    }
                }
            }
        ) // NavDisplay
    }
}
