package com.macaosoftware.nav3playground.common.nav3

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey

abstract class NavBarItem(
    val icon: ImageVector,
    val description: String
) : NavKey