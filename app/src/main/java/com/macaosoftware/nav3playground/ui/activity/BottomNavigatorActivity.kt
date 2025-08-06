package com.macaosoftware.nav3playground.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.macaosoftware.nav3playground.common.NavBarItem
import com.macaosoftware.nav3playground.moduleA.getModuleANavBarItem
import com.macaosoftware.nav3playground.moduleB.getModuleBNavBarItem
import com.macaosoftware.nav3playground.ui.getModuleHomeNavBarItem
import com.macaosoftware.nav3playground.ui.view.BottomBarNavigation

private val TOP_LEVEL_ROUTES: List<NavBarItem> = listOf(
    getModuleHomeNavBarItem(),
    getModuleANavBarItem(),
    getModuleBNavBarItem()
)

class BottomNavigatorActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomBarNavigation(
                navBarItemList = TOP_LEVEL_ROUTES,
                onExit = { finish() }
            )
        }
    }
}
