package com.macaosoftware.nav3playground.common.auth.arch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.macaosoftware.nav3playground.common.arch.FeatureModule
import com.macaosoftware.nav3playground.common.auth.ui.CreateAccountContainer
import com.macaosoftware.nav3playground.common.auth.ui.ForgotPasswordContainer
import com.macaosoftware.nav3playground.common.auth.ui.LoginContainer
import com.macaosoftware.nav3playground.common.auth.ui.LoginContainerCallback
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.Route
import com.macaosoftware.nav3playground.common.ui.navigation.StackNavigator

private typealias EntryProviderBuilderLambda = EntryProviderBuilder<Route>.() -> Unit

private data object Login : NavBarItem(
    icon = Icons.Default.Call,
    description = "Login"
)

private data object CreateAccount : Route
private data object ForgotPassword : Route

class AuthFeatureModule : FeatureModule {

    override fun getEntryPointNavBarItem(): NavBarItem = Login

    fun getModuleAuthEntryProviderBuilder(
        stackNavigator: StackNavigator,
        onResult: (Boolean) -> Unit
    ): EntryProviderBuilderLambda = {
        entry<Login> {
            LoginContainer(
                loginContainerCallback = LoginContainerCallback(
                    onResult = { loginResult ->
                        // Pop the last screen in the auth module before delivering the result
                        stackNavigator.goBack()
                        onResult.invoke(loginResult)
                    },
                    goToCreateAccount = {
                        stackNavigator.pushRouteIntoCurrentTopLevel(
                            route = CreateAccount
                        )
                    },
                    goToForgotPassword = {
                        stackNavigator.pushRouteIntoCurrentTopLevel(
                            route = ForgotPassword
                        )
                    }
                )
            )
        }
        entry<CreateAccount> {
            CreateAccountContainer()
        }
        entry<ForgotPassword> {
            ForgotPasswordContainer()
        }
    }
}
