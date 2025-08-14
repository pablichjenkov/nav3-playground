package com.macaosoftware.nav3playground.common.auth.arch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.macaosoftware.nav3playground.common.arch.FeatureModule
import com.macaosoftware.nav3playground.common.auth.ui.CreateAccountContainer
import com.macaosoftware.nav3playground.common.auth.ui.LoginContainer
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.Route
import com.macaosoftware.nav3playground.common.ui.navigation.StackNavigator

private typealias EntryProviderBuilderLambda = EntryProviderBuilder<Route>.() -> Unit

private data object Login : NavBarItem(
    icon = Icons.Default.Call,
    description = "Login"
)

private data object CreateAccount : NavBarItem(
    icon = Icons.Default.Settings,
    description = "CreateAccount"
)

class FeatureAuthModule : FeatureModule {

    override fun getModuleNavBarItem(): NavBarItem = Login

    fun getModuleAuthEntryProviderBuilder(
        stackNavigator: StackNavigator,
    ): EntryProviderBuilderLambda = {
        entry<Login> {
            LoginContainer()
        }
        entry<CreateAccount> {
            CreateAccountContainer()
        }
    }

}
