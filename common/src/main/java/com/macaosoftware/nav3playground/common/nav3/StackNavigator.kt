package com.macaosoftware.nav3playground.common.nav3

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.NavKey

internal class StackNavigator(
    navBarItemList: List<NavBarItem>,
    val onExit: () -> Unit
) {

    val backStack = mutableStateListOf<NavKey>(navBarItemList[0])

    var currentNavItem by mutableStateOf<NavBarItem>(value = navBarItemList[0])
        private set

    val childrenTopLevelNavigatorMap by lazy {
        mutableMapOf<String, TopLevelNavigator>()
    }

    private val stackToNavBarItemMap: LinkedHashMap<NavBarItem, MutableList<NavKey>> =
        LinkedHashMap()

    private val navBarItemTransactionHistory = mutableListOf<NavBarItem>()

    init {
        navBarItemList.forEach {
            stackToNavBarItemMap[it] = mutableListOf(it)
        }
    }

    fun selectTopLevel(navBarItem: NavBarItem) {

        // Ignore when tapping in the current active bottom bar tab
        if (navBarItem == currentNavItem) return

        stackToNavBarItemMap[navBarItem]?.let { newStack ->
            backStack.apply {
                clear()
                addAll(elements = newStack.toList())
            }

            // Update the history with the current NavBarItem and update the value to the
            // new NavBarItem
            navBarItemTransactionHistory.add(currentNavItem)
            currentNavItem = navBarItem
        }
    }

    fun navigate(
        navBarItem: NavBarItem,
        navKey: NavKey,
        navigationMode: NavigationMode = NavigationMode.NewInstance
    ) {
        when (navigationMode) {
            NavigationMode.NewInstance -> {
                stackToNavBarItemMap[navBarItem]?.let { newStack ->
                    newStack.add(navKey)
                    backStack.add(navKey)
                    currentNavItem = navBarItem
                } ?: run {
                    Log.d("StackNavigator", "navBarItem: $navBarItem, not found")
                }
            }

            NavigationMode.SingleInstance -> {
                stackToNavBarItemMap[navBarItem]?.let { newStack ->
                    val index = newStack.indexOf(navKey)
                    val modifiedNewStack: MutableList<NavKey> = if (index == -1) {
                        newStack.apply { add(navKey) }
                    } else {
                        newStack.subList(0, index + 1)
                    }

                    // If the NavBarItem existed, List::sublist() will return a new List instance.
                    // So we need to keep the Map updated with the new instance.
                    stackToNavBarItemMap[navBarItem] = modifiedNewStack

                    backStack.apply {
                        clear()
                        addAll(elements = modifiedNewStack.toList())
                    }

                    currentNavItem = navBarItem
                }
            }
        }
    }

    fun pushRouteIntoCurrentTopLevel(
        navKey: NavKey,
        navigationMode: NavigationMode = NavigationMode.NewInstance
    ) {
        when (navigationMode) {
            NavigationMode.NewInstance -> {
                stackToNavBarItemMap[currentNavItem]?.let { currentStack ->
                    // Make sure both stacks gets updated
                    currentStack.add(navKey)
                    backStack.add(navKey)
                }
            }

            NavigationMode.SingleInstance -> {
                stackToNavBarItemMap[currentNavItem]?.let { currentStack ->
                    val index = currentStack.indexOf(navKey)
                    val modifiedNewStack: MutableList<NavKey> = if (index == -1) {
                        currentStack.apply { add(navKey) }
                    } else {
                        currentStack.subList(0, index + 1)
                    }

                    // If the NavBarItem existed, List::sublist() will return a new List instance.
                    // So we need to keep the Map updated with the new instance.
                    stackToNavBarItemMap[currentNavItem] = modifiedNewStack

                    backStack.apply {
                        clear()
                        addAll(elements = modifiedNewStack.toList())
                    }
                }
            }
        }
    }

    /**
     * Go back to the previous route. If the current navbarItem stack has only one route,
     * the NavbarItem route itself, then navigate to the previous NavBarItem
     */
    fun goBack() {
        if (backStack.size <= 1) {
            navBarItemTransactionHistory.removeLastOrNull()?.also { previousNavBarItem ->
                stackToNavBarItemMap[previousNavBarItem]?.let { previousStack ->
                    backStack.apply {
                        clear()
                        addAll(elements = previousStack.toList())
                        currentNavItem = previousNavBarItem
                    }
                }
            } ?: run {
                // No more NavBarItems in the history so exit the App
                onExit.invoke()
            }
        } else {
            // Lets pop it in the mirror stack first before the actual removal
            val currentStack = stackToNavBarItemMap[currentNavItem]
            currentStack?.removeAt(currentStack.lastIndex)

            backStack.removeLastOrNull()
        }
    }
}
