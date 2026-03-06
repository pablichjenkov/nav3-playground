package com.macaosoftware.nav3playground.moduleA.arch

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.macaosoftware.nav3playground.common.results.ResultFeed
import com.macaosoftware.nav3playground.common.auth.arch.AuthBlock
import com.macaosoftware.nav3playground.common.nav3.Nav3Block
import com.macaosoftware.nav3playground.common.nav3.NavBarItem
import com.macaosoftware.nav3playground.common.nav3.SingleStackNavigator
import com.macaosoftware.nav3playground.moduleA.ui.view.FeedContainer
import com.macaosoftware.nav3playground.moduleA.ui.view.FeedContainerCallback
import dev.zacsweers.metro.Inject

@Inject
class FeedBlock : Nav3Block {

    // TODO: Inject this using Metro
    private val authFeatureModule = AuthBlock()
    private val authEntryPointRoute = authFeatureModule.entryPointNavBarItem()

    override fun entryPointNavBarItem(): NavBarItem = Feed

    fun EntryProviderScope<NavKey>.install(
        singleStackNavigator: SingleStackNavigator,
        onResult: (ResultFeed) -> Unit
    ) {
        entry<Feed> {
            FeedContainer(
                callback = FeedContainerCallback(
                    goToAuthScreen = {
                        singleStackNavigator.navigate(navKey = authEntryPointRoute)
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
