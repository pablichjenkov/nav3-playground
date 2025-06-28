package com.macaosoftware.nav3playground.moduleA

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry

typealias EntryProviderBuilderLambda = EntryProviderBuilder<NavKey>.() -> Unit

fun getModuleAEntryPoint(): NavKey = RouteAMain

fun getModuleAEntryProviderBuilder(
    backStack: NavBackStack,
    onModuleAResult: () -> Unit
): EntryProviderBuilderLambda = {
    entry<RouteAMain> {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ScreensA(
                modifier = Modifier.padding(innerPadding),
                onNextClick = {
                    backStack.add(RouteAInternal1("123"))
                },
                onResult = onModuleAResult
            )
        }
    }
    entry<RouteAInternal1> {
        Column {
            Greeting("Module A internal screen 1")
            Button(onClick = { backStack.removeLastOrNull() }) {
                Text(
                    text = "Go Back to Module A entry point",
                )
            }
        }
    }
    entry<RouteAInternal2> {
        Column {
            Greeting("Module A internal screen 2")
            Button(onClick = { backStack.removeLastOrNull() }) {
                Text(
                    text = "Go Back to Module A internal screen 1",
                )
            }
        }
    }
}