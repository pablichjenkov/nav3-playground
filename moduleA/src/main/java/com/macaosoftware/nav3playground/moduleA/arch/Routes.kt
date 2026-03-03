package com.macaosoftware.nav3playground.moduleA.arch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.Route
import kotlinx.serialization.Serializable

internal object ChatList : NavBarItem(icon = Icons.Default.Face, description = "Chat list")

internal data object ChatDetail : Route

@Serializable
internal data object RouteAFinal : Route