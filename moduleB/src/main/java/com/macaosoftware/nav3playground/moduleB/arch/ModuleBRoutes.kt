package com.macaosoftware.nav3playground.moduleB.arch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.PlayArrow
import com.macaosoftware.nav3playground.common.nav3.NavBarItem

internal data object Camera : NavBarItem(
    icon = Icons.Default.Call,
    description = "Camera ModB"
)

internal data object PageB0NavItem : NavBarItem(
    icon = Icons.Default.PlayArrow,
    description = "Page B - 0 Nested Display"
)

internal data object PageB1NavItem : NavBarItem(
    icon = Icons.Default.AddCircle,
    description = "Page B - 1 Nested Display"
)

internal data object PageB2NavItem : NavBarItem(
    icon = Icons.Default.Face,
    description = "Page B - 2 Nested Display"
)

// @Serializable
// private data class RouteBFinal(val id: String): NavKey