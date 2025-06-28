package com.macaosoftware.nav3playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.macaosoftware.nav3playground.moduleA.getModuleAEntryPoint
import com.macaosoftware.nav3playground.moduleA.getModuleAEntryProviderBuilder
import com.macaosoftware.nav3playground.moduleB.getModuleBEntryPoint
import com.macaosoftware.nav3playground.moduleB.getModuleBEntryProviderBuilder
import com.macaosoftware.nav3playground.ui.theme.Nav3PlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Nav3PlaygroundTheme {
                val backStack = rememberNavBackStack<NavKey>(getModuleAEntryPoint())

                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryProvider = entryProvider {

                        getModuleAEntryProviderBuilder(
                            backStack = backStack,
                            onModuleAResult = {
                                backStack.add(
                                    getModuleBEntryPoint()
                                )
                            }
                        ).invoke(this)

                        getModuleBEntryProviderBuilder(backStack)
                            .invoke(this)
                    }
                )
            }
        }
    }
}
