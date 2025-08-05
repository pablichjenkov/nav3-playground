/*
 * Copyright 2025 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.macaosoftware.nav3playground

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.macaosoftware.nav3playground.moduleA.ChatList
import com.macaosoftware.nav3playground.moduleA.getModuleAEntryProviderBuilder
import com.macaosoftware.nav3playground.moduleB.Camera
import com.macaosoftware.nav3playground.moduleB.getModuleBEntryProviderBuilder
import kotlinx.coroutines.flow.Flow

private val TOP_LEVEL_ROUTES: List<NavBarItem> = listOf(Home, ChatList, Camera)

class BottomNavigatorActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val stackNavigator = remember { StackNavigator(navBarItemList = TOP_LEVEL_ROUTES) }

            NavigationEventHandler { progress: Flow<NavigationEvent> ->

                Log.d("BottomNavigatorActivity", "NavigationEventHandler called")

                progress.collect { backEvent ->

                    Log.d(
                        "BottomNavigatorActivity",
                        "NavigationEventHandler progress = ${backEvent.progress}"
                    )
                }

                stackNavigator.goBack {
                    finish()
                }

                Log.d(
                    "BottomNavigatorActivity",
                    "NavigationEventHandler collection completed"
                )
            }

            Scaffold(
                topBar = {
                    TopAppBarWithSearch {
                        stackNavigator.navigateInsideCurrentTopLevel(
                            stackNavigator.currentNavItem,
                            Search
                        )
                    }
                },
                bottomBar = {
                    NavigationBar {
                        TOP_LEVEL_ROUTES.forEach { topLevelRoute ->

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
//                    onBack = {
//                        stackNavigator.goBack()
//                    },
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
                                stackNavigator.navigateToTopLevel(Camera)
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
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithSearch(
    onSearchClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text("Navigator Activity")
        },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            }

        },
    )
}
