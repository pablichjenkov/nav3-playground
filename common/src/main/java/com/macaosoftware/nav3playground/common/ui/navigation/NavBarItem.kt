package com.macaosoftware.nav3playground.common.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.macaosoftware.nav3playground.common.ui.navigation.Route

abstract class NavBarItem(
    val icon: ImageVector,
    val description: String
) : Route