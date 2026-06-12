package com.macaosoftware.nav3playground.common.auth.arch

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.macaosoftware.nav3playground.common.auth.ui.CreateAccountContainer
import com.macaosoftware.nav3playground.common.auth.ui.ForgotPasswordContainer
import com.macaosoftware.nav3playground.common.auth.ui.LoginContainer
import com.macaosoftware.nav3playground.common.auth.ui.LoginContainerCallback
import com.macaosoftware.nav3playground.common.nav3.EntryProviderBuilder
import com.macaosoftware.nav3playground.common.nav3.Nav3Block
import com.macaosoftware.nav3playground.common.nav3.Nav3BlockBuilder
import com.macaosoftware.nav3playground.common.nav3.NavBarItem
import com.macaosoftware.nav3playground.common.nav3.SingleStackNavigator
import dev.zacsweers.metro.Inject

@Inject
class AuthBlock : Nav3Block<AuthBlock.Input> {

    override fun entryPointNavBarItem(): NavBarItem = Login

    context(entryProviderScope: EntryProviderScope<NavKey>)
    override fun installEntries(
        input: Input
    ): Nav3BlockBuilder = Nav3BlockBuilder(entryProviderScope = entryProviderScope) {
        entry<Login> {
            LoginContainer(
                loginContainerCallback = LoginContainerCallback(
                    onResult = { loginResult ->
                        // Pop the last screen in the auth module before delivering the result
                        input.singleStackNavigator.goBack()
                        input.onResult.invoke(loginResult)
                    },
                    goToCreateAccount = {
                        input.singleStackNavigator.navigate(navKey = CreateAccount)
                    },
                    goToForgotPassword = {
                        input.singleStackNavigator.navigate(navKey = ForgotPassword)
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

    class Input(
        val singleStackNavigator: SingleStackNavigator,
        val onResult: (Boolean) -> Unit
    )
}
