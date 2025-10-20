package com.macaosoftware.nav3playground.common.search.arch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.macaosoftware.nav3playground.common.arch.FeatureModule
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.Route
import com.macaosoftware.nav3playground.common.ui.navigation.SingleStackNavigator
import com.macaosoftware.nav3playground.common.ui.view.ContentPink
import dev.zacsweers.metro.Inject

private typealias EntryProviderBuilderLambda = EntryProviderBuilder<Route>.() -> Unit

data object SearchNavBarItem : NavBarItem(
    icon = Icons.Default.Search,
    description = "Common NavBar Item"
)

@Inject
class SearchFeatureModule : FeatureModule {

    override fun getEntryPointNavBarItem(): NavBarItem = SearchNavBarItem

    fun getModuleCommonEntryProviderBuilder(
        singleStackNavigator: SingleStackNavigator,
        onResult: () -> Unit
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