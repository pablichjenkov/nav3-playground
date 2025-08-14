package com.macaosoftware.nav3playground.common.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class StackNavigator(
    navBarItemList: List<NavBarItem>,
    val onExit: () -> Unit
) {

    val backStack = mutableStateListOf<Route>(navBarItemList[0])

    var currentNavItem by mutableStateOf<NavBarItem>(value = navBarItemList[0])
        private set

    val childrenStackNavigatorMap by lazy {
        mutableMapOf<String, StackNavigator>()
    }

    private val stackToNavBarItemMap: LinkedHashMap<NavBarItem, MutableList<Route>> =
        LinkedHashMap()

    private val navBarItemTransactionHistory = mutableListOf<NavBarItem>()

    init {
        navBarItemList.forEach {
            stackToNavBarItemMap[it] = mutableListOf(it)
        }
    }

    fun navigateToTopLevel(navBarItem: NavBarItem) {

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

    fun navigateInsideCurrentTopLevel(
        navBarItem: NavBarItem,
        route: Route,
        navigationMode: NavigationMode = NavigationMode.NewInstance
    ) {
        when (navigationMode) {
            NavigationMode.NewInstance -> {
                stackToNavBarItemMap[navBarItem]?.let { newStack ->
                    // Only update if the NavBarItem is the same as the currentNavItem
                    // Is not practical and illegal to modify a stack which is not the
                    // current stack.
                    if (navBarItem != currentNavItem) throw IllegalStateException()
                    newStack.add(route)
                    backStack.add(route)
                    currentNavItem = navBarItem
                }
            }

            NavigationMode.SingleInstance -> {
                stackToNavBarItemMap[navBarItem]?.let { newStack ->
                    val index = newStack.indexOf(navBarItem)
                    val modifiedNewStack: MutableList<Route> = if (index == -1) {
                        newStack.apply { add(route) }
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
