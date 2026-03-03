package com.macaosoftware.nav3playground.moduleA.arch

import com.macaosoftware.nav3playground.common.arch.ResultFeed
import com.macaosoftware.nav3playground.common.auth.arch.ModuleAuthNav3Graph
import com.macaosoftware.nav3playground.common.ui.navigation.EntryProviderScopeLambda
import com.macaosoftware.nav3playground.common.ui.navigation.Nav3Graph
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.SingleStackNavigator
import com.macaosoftware.nav3playground.moduleA.ui.view.FeedContainer
import com.macaosoftware.nav3playground.moduleA.ui.view.FeedContainerCallback
import dev.zacsweers.metro.Inject

@Inject
class FeedNav3Graph : Nav3Graph {

    // TODO: Inject this using Metro
    private val authFeatureModule = ModuleAuthNav3Graph()
    private val authEntryPointRoute = authFeatureModule.entryPointNavBarItem()

    override fun entryPointNavBarItem(): NavBarItem = Feed

    fun entryProviderBuilder(
        singleStackNavigator: SingleStackNavigator,
        onResult: (ResultFeed) -> Unit
    ): EntryProviderScopeLambda = {
        entry<Feed> {
            FeedContainer(
                callback = FeedContainerCallback(
                    goToAuthScreen = {
                        singleStackNavigator.navigate(route = authEntryPointRoute)
                    }
                )
            )
        }

        authFeatureModule.entryProviderBuilder(
            singleStackNavigator = singleStackNavigator,
            onResult = { loginResult ->
                if (loginResult) {
                    // Show feed content. Don't have to do anything. It will land in the Feed
                    // Route
                } else {
                    // Show login failed Modal
                }
            }
        ).invoke(this)
    }
}
