package com.macaosoftware.nav3playground.arch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.macaosoftware.nav3playground.common.arch.FeatureModule
import com.macaosoftware.nav3playground.common.auth.arch.FeatureAuthModule
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.Route
import com.macaosoftware.nav3playground.common.ui.navigation.StackNavigator
import com.macaosoftware.nav3playground.common.ui.view.ContentRed

private typealias EntryProviderBuilderLambda = EntryProviderBuilder<Route>.() -> Unit

private data object Home : NavBarItem(icon = Icons.Default.Home, description = "Home")

class FeatureAppModule : FeatureModule {

    // TODO: Inject this using Metro
    private val featureAuthModule = FeatureAuthModule()
    private val authEntryPointRoute = featureAuthModule.getModuleNavBarItem()

    override fun getModuleNavBarItem(): NavBarItem = Home

    fun getModuleAppEntryProviderBuilder(
        stackNavigator: StackNavigator
    ): EntryProviderBuilderLambda = {
        entry<Home> {
            ContentRed(title ="Unauthenticated Home Screen") {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Please Login to be able to see the Home feed",
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(height = 56.dp))
                    Button(onClick = {
                        stackNavigator.navigateInsideCurrentTopLevel(
                            navBarItem = Home,
                            route = authEntryPointRoute
                        )
                    }) {
                        Text("Go to Auth")
                    }
                }
            }
        }

        featureAuthModule.getModuleAuthEntryProviderBuilder(
            stackNavigator = stackNavigator
        ).invoke(this)
    }
}