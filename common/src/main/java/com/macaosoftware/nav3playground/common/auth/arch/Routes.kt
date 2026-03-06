package com.macaosoftware.nav3playground.common.auth.arch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.navigation3.runtime.NavKey
import com.macaosoftware.nav3playground.common.nav3.NavBarItem

internal data object Login : NavBarItem(
    icon = Icons.Default.Call,
    description = "Login"
)

internal data object CreateAccount: NavKey

internal data object ForgotPassword: NavKey