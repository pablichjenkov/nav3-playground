package com.macaosoftware.nav3playground.moduleA.arch

import androidx.navigation3.runtime.EntryProviderScope
import com.macaosoftware.nav3playground.common.arch.ResultFeed
import com.macaosoftware.nav3playground.common.auth.arch.AuthNode
import com.macaosoftware.nav3playground.common.ui.navigation.Nav3Node
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.Route
import com.macaosoftware.nav3playground.common.ui.navigation.SingleStackNavigator
import com.macaosoftware.nav3playground.moduleA.ui.view.FeedContainer
import com.macaosoftware.nav3playground.moduleA.ui.view.FeedContainerCallback
import dev.zacsweers.metro.Inject

@Inject
class FeedNode : Nav3Node {

    // TODO: Inject this using Metro
    private val authFeatureModule = AuthNode()
    private val authEntryPointRoute = authFeatureModule.entryPointNavBarItem()

    override fun entryPointNavBarItem(): NavBarItem = Feed

    fun EntryProviderScope<Route>.install(
        singleStackNavigator: SingleStackNavigator,
        onResult: (ResultFeed) -> Unit
    ) {
        entry<Feed> {
            FeedContainer(
                callback = FeedContainerCallback(
                    goToAuthScreen = {
                        singleStackNavigator.navigate(route = authEntryPointRoute)
                    }
                )
            )
        }

        with(receiver = authFeatureModule) {
            install(
                singleStackNavigator = singleStackNavigator,
                onResult = { loginResult ->
                    if (loginResult) {
                        // Show feed content. Don't have to do anything. It will land in the Feed
                        // Route
                    } else {
                        // Show login failed Modal
                    }
                }
            )
        }
    }

}
