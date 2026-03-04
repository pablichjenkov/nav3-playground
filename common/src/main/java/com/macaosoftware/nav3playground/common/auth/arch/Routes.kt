package com.macaosoftware.nav3playground.common.auth.arch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.Route

internal data object Login : NavBarItem(
    icon = Icons.Default.Call,
    description = "Login"
)

internal data object CreateAccount : Route

internal data object ForgotPassword : Route