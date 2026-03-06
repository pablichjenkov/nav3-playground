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
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationEventHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.macaosoftware.nav3playground.common.results.ResultA
import com.macaosoftware.nav3playground.common.results.ResultB
import com.macaosoftware.nav3playground.common.search.arch.SearchNavBarItem
import com.macaosoftware.nav3playground.common.search.arch.SearchBlock
import com.macaosoftware.nav3playground.common.nav3.LocalResultStore
import com.macaosoftware.nav3playground.common.nav3.Nav3Block
import com.macaosoftware.nav3playground.common.nav3.NavBarItem
import com.macaosoftware.nav3playground.common.nav3.TopLevelNavigator
import com.macaosoftware.nav3playground.moduleA.arch.FeedBlock
import com.macaosoftware.nav3playground.moduleA.arch.ModuleABlock
import com.macaosoftware.nav3playground.moduleB.arch.ModuleBBlock

@Composable
fun HomeContainer(
    nav3BlockList: List<Nav3Block>,
    navBarItemList: List<NavBarItem>,
    onExit: () -> Unit
) {

    val topLevelNavigator = remember {
        TopLevelNavigator(
            navBarItemList = navBarItemList,
            onExit = onExit
        )
    }

    NavigationEventHandler(
        state = rememberNavigationEventState(currentInfo = NavigationEventInfo.None),
        isForwardEnabled = false,
        onBackCancelled = {
            Log.d(
                "HomeContainer",
                "NavigationEventHandler::onBackCancelled"
            )
        },
        onBackCompleted = {
            Log.d(
                "HomeContainer",
                "NavigationEventHandler::onBackCompleted"
            )
            topLevelNavigator.goBack()
        }
    )

    Scaffold(
        topBar = {
            TopAppBarWithSearch {
                topLevelNavigator.pushRouteIntoCurrentTopLevel(
                    navKey = (SearchNavBarItem as NavKey)
                )
            }
        },
        bottomBar = {
            NavigationBar {
                navBarItemList.forEach { topLevelRoute ->

                    val isSelected = topLevelRoute == topLevelNavigator.currentNavItem

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            topLevelNavigator.selectTopLevel(navBarItem = topLevelRoute)
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
            backStack = topLevelNavigator.backStack,
            onBack = {
                /**
                 * This onBack is called whenever the backstack is greater than 1.
                 * When the stack is 1 item, the NavigationEventHandler is called instead
                 * */
                topLevelNavigator.goBack()
            },
            entryProvider = entryProvider {
                val localResultStore = LocalResultStore.current
                nav3BlockList.forEach { featureModule ->
                    when (featureModule) {

                        // Add Module Common Routes
                        is SearchBlock -> {
                            with(receiver = featureModule) {
                                install(
                                    singleStackNavigator = topLevelNavigator.getSingleStackNavigator(
                                        navBarItem = featureModule.entryPointNavBarItem()
                                    ),
                                    onResult = {}
                                )
                            }
                        }

                        // Add Module A Routes
                        is ModuleABlock -> {
                            with(receiver = featureModule) {
                                install(
                                    singleStackNavigator = topLevelNavigator.getSingleStackNavigator(
                                        navBarItem = featureModule.entryPointNavBarItem()
                                    ),
                                    onResult = {
                                        // Set a ResultA for the coming screen to be resumed
                                        localResultStore.setResult<ResultA>(result = it)

                                        // Programmatically navigate from module A to B
                                        topLevelNavigator.selectTopLevel(
                                            navBarItem = navBarItemList[2]
                                        )
                                    }
                                )
                            }
                        }
                        // Add Module B Routes
                        is ModuleBBlock -> {
                            with(receiver = featureModule) {
                                install(
                                    singleStackNavigator = topLevelNavigator.getSingleStackNavigator(
                                        navBarItem = featureModule.entryPointNavBarItem()
                                    ),
                                    onResult = {
                                        // Set a ResultB for the coming screen to be resumed
                                        localResultStore.setResult<ResultB>(result = it)

                                        topLevelNavigator.goBack()
                                    }
                                )
                            }
                        }

                        // Add Module Feed Routes
                        is FeedBlock -> {
                            with(receiver = featureModule) {
                                install(
                                    singleStackNavigator = topLevelNavigator.getSingleStackNavigator(
                                        navBarItem = featureModule.entryPointNavBarItem()
                                    ),
                                    onResult = {}
                                )
                            }
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
