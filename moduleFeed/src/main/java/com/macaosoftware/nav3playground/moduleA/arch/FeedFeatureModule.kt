package com.macaosoftware.nav3playground.moduleA.arch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.navigation3.runtime.EntryProviderScope
import com.macaosoftware.nav3playground.common.arch.FeatureModule
import com.macaosoftware.nav3playground.common.auth.arch.AuthFeatureModule
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.Route
import com.macaosoftware.nav3playground.common.ui.navigation.SingleStackNavigator
import com.macaosoftware.nav3playground.moduleA.ui.view.FeedContainer
import com.macaosoftware.nav3playground.moduleA.ui.view.FeedContainerCallback
import dev.zacsweers.metro.Inject

private typealias EntryProviderScopeLambda = EntryProviderScope<Route>.() -> Unit

internal data object Feed : NavBarItem(icon = Icons.Default.Star, description = "Feed")

@Inject
class FeedFeatureModule : FeatureModule {

    // TODO: Inject this using Metro
    private val authFeatureModule = AuthFeatureModule()
    private val authEntryPointRoute = authFeatureModule.getEntryPointNavBarItem()

    override fun getEntryPointNavBarItem(): NavBarItem = Feed

    fun getModuleFeedEntryProviderBuilder(
        singleStackNavigator: SingleStackNavigator,
        onResult: () -> Unit
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

        authFeatureModule.getModuleAuthEntryProviderBuilder(
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
