package com.macaosoftware.nav3playground.common.arch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.macaosoftware.nav3playground.common.ui.view.ContentPink
import com.macaosoftware.nav3playground.common.ui.navigation.StackNavigator
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.Route

private typealias EntryProviderBuilderLambda = EntryProviderBuilder<Route>.() -> Unit

data object SearchNavBarItem : NavBarItem(
    icon = Icons.Default.Search,
    description = "Common NavBar Item"
)

class CommonFeatureModule : FeatureModule {

    override fun getModuleNavBarItem(): NavBarItem = SearchNavBarItem

    fun getModuleCommonEntryProviderBuilder(
        stackNavigator: StackNavigator
    ): EntryProviderBuilderLambda = {
        entry<SearchNavBarItem> {
            ContentPink("Search Screen") {
                var text by rememberSaveable { mutableStateOf("") }
                TextField(
                    value = text,
                    onValueChange = { newText -> text = newText },
                    label = { Text("Search something ...") },
                    singleLine = true
                )
            }
        }
    }
}