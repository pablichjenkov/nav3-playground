package com.macaosoftware.nav3playground.moduleB.arch

import android.util.Log
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.macaosoftware.nav3playground.common.nav3.LocalResultStore
import com.macaosoftware.nav3playground.common.nav3.Nav3Block
import com.macaosoftware.nav3playground.common.nav3.NavBarItem
import com.macaosoftware.nav3playground.common.nav3.ResultStore
import com.macaosoftware.nav3playground.common.nav3.SingleResultEffect
import com.macaosoftware.nav3playground.common.nav3.SingleStackNavigator
import com.macaosoftware.nav3playground.common.results.ResultA
import com.macaosoftware.nav3playground.common.results.ResultB
import com.macaosoftware.nav3playground.common.results.ResultFeed
import com.macaosoftware.nav3playground.common.results.ResultSearch
import com.macaosoftware.nav3playground.moduleB.ui.ModuleBDrawerNavigation
import dev.zacsweers.metro.Inject

@Inject
class ModuleBBlock(
    moduleBBlockGraphFactory: ModuleBBlockGraph.Factory
) : Nav3Block {

    val screenB0ViewModel = moduleBBlockGraphFactory.createModuleBNodeGraph().screenB0ViewModel

    override fun entryPointNavBarItem(): NavBarItem = Camera

    fun EntryProviderScope<NavKey>.install(
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
                screenB0ViewModel = screenB0ViewModel,
                onExit = { singleStackNavigator.goBack() }
            )
        }
    }

}
