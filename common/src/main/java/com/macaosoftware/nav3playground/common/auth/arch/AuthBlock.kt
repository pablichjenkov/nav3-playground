package com.macaosoftware.nav3playground.common.auth.arch

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.macaosoftware.nav3playground.common.auth.ui.CreateAccountContainer
import com.macaosoftware.nav3playground.common.auth.ui.ForgotPasswordContainer
import com.macaosoftware.nav3playground.common.auth.ui.LoginContainer
import com.macaosoftware.nav3playground.common.auth.ui.LoginContainerCallback
import com.macaosoftware.nav3playground.common.nav3.Nav3Block
import com.macaosoftware.nav3playground.common.nav3.NavBarItem
import com.macaosoftware.nav3playground.common.nav3.SingleStackNavigator
import dev.zacsweers.metro.Inject

@Inject
class AuthBlock : Nav3Block {

    override fun entryPointNavBarItem(): NavBarItem = Login

    fun EntryProviderScope<NavKey>.install(
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
                        singleStackNavigator.navigate(navKey = CreateAccount)
                    },
                    goToForgotPassword = {
                        singleStackNavigator.navigate(navKey = ForgotPassword)
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
