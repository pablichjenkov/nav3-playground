package com.macaosoftware.nav3playground.moduleA.arch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.navigation3.runtime.NavKey
import com.macaosoftware.nav3playground.common.nav3.NavBarItem
import kotlinx.serialization.Serializable

internal object ChatList : NavBarItem(icon = Icons.Default.Face, description = "Chat list")

internal data object ChatDetail: NavKey

@Serializable
internal data object RouteAFinal: NavKey