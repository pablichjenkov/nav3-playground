package com.macaosoftware.nav3playground.moduleB.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationEventHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.SingleStackNavigator
import com.macaosoftware.nav3playground.common.ui.navigation.TopLevelNavigator
import com.macaosoftware.nav3playground.moduleB.arch.PageB0NavItem
import com.macaosoftware.nav3playground.moduleB.arch.PageB1NavItem
import com.macaosoftware.nav3playground.moduleB.arch.PageB2NavItem
import com.macaosoftware.nav3playground.moduleB.ui.view.ScreenB0
import com.macaosoftware.nav3playground.moduleB.ui.view.ScreenB1
import com.macaosoftware.nav3playground.moduleB.ui.view.ScreenB2
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModuleBDrawerNavigation(
    parentStackNavigator: SingleStackNavigator,
    navBarItemList: List<NavBarItem>,
    onExit: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val topLevelNavigator = remember {
        parentStackNavigator.childrenStackNavigatorMap.getOrPut(
            key = "ModuleBDrawerNavigation",
            defaultValue = {
                TopLevelNavigator(
                    navBarItemList = navBarItemList,
                    onExit = onExit
                )
            }
        )
    }

    NavigationEventHandler(
        state = rememberNavigationEventState(currentInfo = NavigationEventInfo.None),
        isForwardEnabled = false,
        onBackCancelled = {
            Log.d(
                "DrawerNavigation",
                "NavigationEventHandler::onBackCancelled"
            )
        },
        onBackCompleted = {
            Log.d(
                "DrawerNavigation",
                "NavigationEventHandler::onBackCompleted"
            )
            topLevelNavigator.goBack()
        }
    )

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "Drawer Title",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider()

                    Text(
                        "Section 1",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    NavigationDrawerItem(
                        label = { Text("Page 0") },
                        selected = false,
                        onClick = {
                            topLevelNavigator.selectTopLevel(
                                navBarItem = PageB0NavItem
                            )
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Page 1") },
                        selected = false,
                        onClick = {
                            topLevelNavigator.selectTopLevel(
                                navBarItem = PageB1NavItem
                            )
                        }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    Text(
                        "Section 2",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    NavigationDrawerItem(
                        label = { Text("Page 2") },
                        selected = false,
                        icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                        badge = { Text("20") }, // Placeholder
                        onClick = {
                            topLevelNavigator.selectTopLevel(
                                navBarItem = PageB2NavItem
                            )
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Help and feedback") },
                        selected = false,
                        icon = {
                            Icon(
                                Icons.AutoMirrored.Outlined.Send,
                                contentDescription = null
                            )
                        },
                        onClick = {

                        },
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Drawer with nested Display") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(Icons.Default.AccountCircle, contentDescription = "Menu")
                        }
                    }
                )
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

                    entry<PageB0NavItem> {
                        /*ContentPurple("Module B") {
                            Button(onClick = {
                                stackNavigator.navigateInsideCurrentTopLevel(
                                    navBarItem = Camera,
                                    route = RouteBFinal(id = "12345")
                                )
                            }) {
                                Text("Go to nested nav-display")
                            }
                        }*/
                        ScreenB0()
                    }

                    entry<PageB1NavItem> {
                        ScreenB1()
                    }

                    entry<PageB2NavItem> {
                        ScreenB2()
                    }
                }
            )
        }
    }
}
