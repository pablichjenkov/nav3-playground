package com.macaosoftware.nav3playground.common.auth.arch

import androidx.navigation3.runtime.EntryProviderScope
import com.macaosoftware.nav3playground.common.auth.ui.CreateAccountContainer
import com.macaosoftware.nav3playground.common.auth.ui.ForgotPasswordContainer
import com.macaosoftware.nav3playground.common.auth.ui.LoginContainer
import com.macaosoftware.nav3playground.common.auth.ui.LoginContainerCallback
import com.macaosoftware.nav3playground.common.ui.navigation.Nav3Block
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.Route
import com.macaosoftware.nav3playground.common.ui.navigation.SingleStackNavigator
import dev.zacsweers.metro.Inject

@Inject
class AuthBlock : Nav3Block {

    override fun entryPointNavBarItem(): NavBarItem = Login

    fun EntryProviderScope<Route>.install(
        singleStackNavigator: SingleStackNavigator,
        onResult: (Boolean) -> Unit
    ) {
        entry<Login> {
            LoginContainer(
                loginContainerCallback = LoginContainerCallback(
                    onResult = { loginResult ->
                        // Pop the last screen in the auth module before delivering the result
                        singleStackNavigator.goBack()
                        onResult.invoke(loginResult)
                    },
                    goToCreateAccount = {
                        singleStackNavigator.navigate(route = CreateAccount)
                    },
                    goToForgotPassword = {
                        singleStackNavigator.navigate(route = ForgotPassword)
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
