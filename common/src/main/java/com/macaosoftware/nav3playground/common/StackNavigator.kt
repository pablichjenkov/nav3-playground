package com.macaosoftware.nav3playground.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * This class models navigation behavior. It provides a back stack
 * as a Compose snapshot-state backed list that can be used with a `NavDisplay`.
 *
 * It supports a single level of nested navigation. Top level
 * routes can be defined using the `Route` class and setting
 * `isTopLevel` to `true`. It also supports shared routes.
 * These are routes that can be nested under multiple top level
 * routes, though only one instance of the route will ever be
 * present in the stack. Shared routes can be defined using
 * `Route.isShared`.
 *
 * The start route is always the first item in the back stack and
 * cannot be moved. Navigating to the start route removes all other
 * top level routes and their associated stacks.
 *
 * @param startRoute - The start route for the back stack.
 * @param canTopLevelRoutesExistTogether - Determines whether other
 * top level routes can exist together on the back stack. Default `false`,
 * meaning other top level routes (and their stacks) will be popped off
 * the back stack when navigating to a top level route.
 *
 * For example, if A, B and C are all top level routes:
 *
 * ```
 * val navigator = Navigator<Route>(startRoute = A) // back stack is [A]
 * navigator.navigate(B) // back stack [A, B]
 * navigator.navigate(C) // back stack [A, C] - B is popped before C is added
 *
 * When set to `true`, the resulting back stack would be [A, B, C]
 * ```
 *
 * @see `NavigatorTest`.
 */
class StackNavigator(
    navBarItemList: List<NavBarItem>
) {

    val backStack = mutableStateListOf<Route>(navBarItemList[0])

    var currentNavItem by mutableStateOf<NavBarItem>(value = navBarItemList[0])
        private set

    private val stackToNavBarItemMap: LinkedHashMap<NavBarItem, MutableList<Route>> =
        LinkedHashMap<NavBarItem, MutableList<Route>>()

    private val navBarItemTransactionHistory = mutableListOf<NavBarItem>()

    init {
        navBarItemList.forEach {
            stackToNavBarItemMap[it] = mutableListOf(it)
        }
    }

    fun navigateToTopLevel(navBarItem: NavBarItem) {
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
                    if (navBarItem != currentNavItem)  throw IllegalStateException()
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
    fun goBack(
        onFinished: () -> Unit
    ) {
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
                onFinished.invoke()
            }
        } else {
            backStack.removeLastOrNull()

            // Lets update the mirror list on the map
            val currentStack = stackToNavBarItemMap[currentNavItem]
            currentStack?.removeAt(currentStack.lastIndex)
        }
    }
}

abstract class Route

abstract class NavBarItem(
    val icon: ImageVector,
    val description: String
) : Route()

enum class NavigationMode {
    NewInstance,
    SingleInstance
}
