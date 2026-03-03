package com.macaosoftware.nav3playground.common.search.arch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.macaosoftware.nav3playground.common.ui.navigation.EntryProviderScopeLambda
import com.macaosoftware.nav3playground.common.ui.navigation.Nav3Graph
import com.macaosoftware.nav3playground.common.ui.navigation.NavBarItem
import com.macaosoftware.nav3playground.common.ui.navigation.SingleStackNavigator
import com.macaosoftware.nav3playground.common.ui.view.ContentPink
import dev.zacsweers.metro.Inject

data object SearchNavBarItem : NavBarItem(
    icon = Icons.Default.Search,
    description = "Common NavBar Item"
)

@Inject
class SearchNav3Graph : Nav3Graph {

    override fun entryPointNavBarItem(): NavBarItem = SearchNavBarItem

    fun entryProviderBuilder(
        singleStackNavigator: SingleStackNavigator,
        onResult: () -> Unit
    ): EntryProviderScopeLambda = {
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