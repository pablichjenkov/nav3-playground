package com.macaosoftware.nav3playground.moduleB.arch

import android.util.Log
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.EntryProviderScope
import com.macaosoftware.nav3playground.common.arch.ResultA
import com.macaosoftware.nav3playground.common.arch.ResultB
import com.macaosoftware.nav3playground.common.arch.ResultFeed
import com.macaosoftware.nav3playground.common.arch.ResultSearch
import com.macaosoftware.nav3playground.common.ui.navigation.LocalResultStore
import com.macaosoftware.nav3playground.common.ui.navigation.Nav3Block
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.ResultStore
import com.macaosoftware.nav3playground.common.ui.navigation.Route
import com.macaosoftware.nav3playground.common.ui.navigation.SingleResultEffect
import com.macaosoftware.nav3playground.common.ui.navigation.SingleStackNavigator
import com.macaosoftware.nav3playground.moduleB.ui.ModuleBDrawerNavigation
import dev.zacsweers.metro.Inject

@Inject
class ModuleBBlock : Nav3Block {

    override fun entryPointNavBarItem(): NavBarItem = Camera

    fun EntryProviderScope<Route>.install(
        singleStackNavigator: SingleStackNavigator,
        onResult: (ResultB) -> Unit
    ) {
        entry<Camera> {
            val resultStore: ResultStore = LocalResultStore.current
            SingleResultEffect<ResultFeed> {

            }
            SingleResultEffect<ResultA> {
                Log.d(
                    "HomeContainer",
                    "FeatureB received Result from A = ${it.data}"
                )
            }
            SingleResultEffect<ResultSearch> {

            }

            val navBarItemList = remember {
                listOf(PageB0NavItem, PageB1NavItem, PageB2NavItem)
            }

            ModuleBDrawerNavigation(
                parentStackNavigator = singleStackNavigator,
                navBarItemList = navBarItemList,
                onExit = { singleStackNavigator.goBack() }
            )
        }
    }

}
