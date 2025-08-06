package com.macaosoftware.nav3playground.ui.view

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.navigationevent.NavigationEvent
import androidx.navigationevent.compose.NavigationEventHandler
import com.macaosoftware.nav3playground.common.NavBarItem
import com.macaosoftware.nav3playground.common.Search
import com.macaosoftware.nav3playground.common.StackNavigator
import com.macaosoftware.nav3playground.common.getModuleCommonEntryProviderBuilder
import com.macaosoftware.nav3playground.moduleA.getModuleAEntryProviderBuilder
import com.macaosoftware.nav3playground.moduleB.getModuleBEntryProviderBuilder
import com.macaosoftware.nav3playground.ui.getModuleAppEntryProviderBuilder
import kotlinx.coroutines.flow.Flow

@Composable
fun BottomBarNavigation(
    navBarItemList: List<NavBarItem>,
    onExit: () -> Unit
) {
    val stackNavigator = remember { StackNavigator(navBarItemList = navBarItemList) }

    NavigationEventHandler { progress: Flow<NavigationEvent> ->

        Log.d("BottomBarNavigation", "NavigationEventHandler called")

        progress.collect { backEvent ->

            Log.d(
                "BottomBarNavigation",
                "NavigationEventHandler progress = ${backEvent.progress}"
            )
        }

        stackNavigator.goBack {
            onExit.invoke()
        }

        Log.d(
            "BottomBarNavigation",
            "NavigationEventHandler collection completed"
        )
    }

    Scaffold(
        topBar = {
            TopAppBarWithSearch {
                stackNavigator.navigateInsideCurrentTopLevel(
                    navBarItem = stackNavigator.currentNavItem,
                    route = Search
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
                stackNavigator.goBack({})
            },
            entryProvider = entryProvider {

                // Add Module App Routes
                getModuleAppEntryProviderBuilder(
                    stackNavigator = stackNavigator
                ).invoke(this)

                // Add Module Common Routes
                getModuleCommonEntryProviderBuilder(
                    stackNavigator = stackNavigator
                ).invoke(this)

                // Add Module A Routes
                getModuleAEntryProviderBuilder(
                    stackNavigator = stackNavigator,
                    onModuleAResult = {
                        // Programmatically navigate to module A to B
                        stackNavigator.navigateToTopLevel(
                            navBarItem = navBarItemList[2]
                        )
                    }
                ).invoke(this)

                // Add Module B Routes
                getModuleBEntryProviderBuilder(
                    stackNavigator = stackNavigator
                ).invoke(this)
            },
        )
    }
}
