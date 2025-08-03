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
import android.window.OnBackInvokedCallback
import android.window.OnBackInvokedDispatcher
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

private data object Home : NavBarItem(icon = Icons.Default.Home, description = "Home")
private data object ChatList : NavBarItem(icon = Icons.Default.Face, description = "Chat list")
private data object ChatDetail : Route()
private data object Camera : NavBarItem(icon = Icons.Default.PlayArrow, description = "Camera")
private data object Search : Route()

private val TOP_LEVEL_ROUTES: List<NavBarItem> = listOf(Home, ChatList, Camera)

class BottomNavigatorActivity : ComponentActivity() {

    val stackNavigator = StackNavigator(navBarItemList = TOP_LEVEL_ROUTES)

    val onBackInvokedCallback = OnBackInvokedCallback {
        stackNavigator.goBack {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getOnBackInvokedDispatcher().registerOnBackInvokedCallback(
            OnBackInvokedDispatcher.PRIORITY_DEFAULT,
            onBackInvokedCallback
        )

        enableEdgeToEdge()
        setContent {
//            val stackNavigator = remember {
//                StackNavigator(listOf(Home, ChatList, Camera))
//            }

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
                        entry<Home> {
                            ContentRed("Home screen")
                        }
                        entry<ChatList> {
                            ContentGreen("Chat list screen") {
                                Button(onClick = {
                                    stackNavigator.navigateInsideCurrentTopLevel(
                                        ChatList,
                                        ChatDetail
                                    )
                                }) {
                                    Text("Go to conversation")
                                }
                            }
                        }
                        entry<ChatDetail> {
                            ContentBlue("Chat detail screen")

                        }
                        entry<Camera> {
                            ContentPurple("Camera screen")
                        }
                        entry<Search> {
                            ContentPink("Search screen") {
                                var text by rememberSaveable { mutableStateOf("") }
                                TextField(
                                    value = text,
                                    onValueChange = { newText -> text = newText },
                                    label = { Text("Enter search here") },
                                    singleLine = true
                                )
                            }
                        }
                    },
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(onBackInvokedCallback);
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
