package com.macaosoftware.nav3playground.common

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry

private typealias EntryProviderBuilderLambda = EntryProviderBuilder<Route>.() -> Unit

data object Search : Route()

fun getModuleCommonEntryPoint(): Route = Search

fun getModuleCommonEntryProviderBuilder(
    stackNavigator: StackNavigator
): EntryProviderBuilderLambda = {
    entry<Search> {
        ContentPink("Search screen") {
            var text by rememberSaveable { mutableStateOf("") }
            TextField(
                value = text,
                onValueChange = { newText -> text = newText },
                label = { Text("Enter search here") },
                singleLine = true
            )
        }
    }
}
