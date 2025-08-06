package com.macaosoftware.nav3playground.moduleB

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.navigationevent.NavigationEvent
import androidx.navigationevent.compose.NavigationEventHandler
import com.macaosoftware.nav3playground.common.ContentPink
import com.macaosoftware.nav3playground.common.ContentYellow
import com.macaosoftware.nav3playground.common.NavBarItem
import com.macaosoftware.nav3playground.common.Search
import com.macaosoftware.nav3playground.common.StackNavigator
import com.macaosoftware.nav3playground.common.getModuleCommonEntryProviderBuilder
import kotlinx.coroutines.flow.Flow

@Composable
fun BottomBarNavigationNested(
    navBarItemList: List<NavBarItem>,
    onExit: () -> Unit
) {
    val stackNavigator = remember { StackNavigator(navBarItemList = navBarItemList) }

    NavigationEventHandler { progress: Flow<NavigationEvent> ->

        Log.d("BottomBarNavigationNested", "NavigationEventHandler called")

        progress.collect { backEvent ->

            Log.d(
                "BottomBarNavigationNested",
                "NavigationEventHandler progress = ${backEvent.progress}"
            )
        }

        stackNavigator.goBack {
            onExit.invoke()
        }

        Log.d(
            "BottomBarNavigationNested",
            "NavigationEventHandler collection completed"
        )
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable {
                        stackNavigator.navigateInsideCurrentTopLevel(
                            stackNavigator.currentNavItem,
                            Search
                        )
                    }
            ) {
                Text(text = "Nested NavDisplay")
            }
        },
        bottomBar = {
            NavigationBar {
                navBarItemList.forEach { topLevelRoute ->

                    val isSelected = topLevelRoute == stackNavigator.currentNavItem
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            stackNavigator.navigateToTopLevel(topLevelRoute)
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
                stackNavigator.goBack({})
            },
            entryProvider = entryProvider {

                // Add Module Common Routes
                getModuleCommonEntryProviderBuilder(
                    stackNavigator = stackNavigator
                ).invoke(this)

                // Add Nested Routes
                entry<CameraNested> {
                    ContentYellow(
                    modifier = Modifier.fillMaxSize().background(Color.Yellow),
                        title = "Nested Content"
                    )
                }

            },
        )
    }
}
