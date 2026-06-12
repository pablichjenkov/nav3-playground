package com.macaosoftware.nav3playground.moduleA.arch

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.macaosoftware.nav3playground.common.auth.arch.AuthBlock
import com.macaosoftware.nav3playground.common.nav3.Nav3Block
import com.macaosoftware.nav3playground.common.nav3.Nav3BlockBuilder
import com.macaosoftware.nav3playground.common.nav3.NavBarItem
import com.macaosoftware.nav3playground.common.nav3.SingleStackNavigator
import com.macaosoftware.nav3playground.common.results.ResultFeed
import com.macaosoftware.nav3playground.moduleA.ui.view.FeedContainer
import com.macaosoftware.nav3playground.moduleA.ui.view.FeedContainerCallback
import dev.zacsweers.metro.Inject

@Inject
class FeedBlock : Nav3Block<FeedBlock.Input> {

    // TODO: Inject this using Metro
    private val authBlock = AuthBlock()
    private val authEntryPointRoute = authBlock.entryPointNavBarItem()

    override fun entryPointNavBarItem(): NavBarItem = Feed

    context(entryProviderScope: EntryProviderScope<NavKey>)
    override fun installEntries(
        input: Input
    ): Nav3BlockBuilder = Nav3BlockBuilder(entryProviderScope = entryProviderScope) {
        entry<Feed> {
            FeedContainer(
                callback = FeedContainerCallback(
                    goToAuthScreen = {
                        input.singleStackNavigator.navigate(navKey = authEntryPointRoute)
                    }
                )
            )
        }
        authBlock.installEntries(
            input = AuthBlock.Input(
                singleStackNavigator = input.singleStackNavigator,
                onResult = { loginResult ->
                    if (loginResult) {
                        // Show feed content. Don't have to do anything. It will land in the Feed
                        // Route
                    } else {
                        // Show login failed Modal
                    }
                }
            )
        )
    }

    class Input(
        val singleStackNavigator: SingleStackNavigator,
        val onResult: (ResultFeed) -> Unit
    )
}
