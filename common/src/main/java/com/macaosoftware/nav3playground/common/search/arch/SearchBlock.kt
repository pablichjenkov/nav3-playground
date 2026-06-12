package com.macaosoftware.nav3playground.common.search.arch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.macaosoftware.nav3playground.common.nav3.EntryProviderBuilder
import com.macaosoftware.nav3playground.common.nav3.Nav3Block
import com.macaosoftware.nav3playground.common.nav3.Nav3BlockBuilder
import com.macaosoftware.nav3playground.common.nav3.NavBarItem
import com.macaosoftware.nav3playground.common.nav3.SingleStackNavigator
import com.macaosoftware.nav3playground.common.results.ResultFeed
import com.macaosoftware.nav3playground.common.ui.view.ContentPink
import dev.zacsweers.metro.Inject

data object SearchNavBarItem : NavBarItem(
    icon = Icons.Default.Search,
    description = "Common NavBar Item"
)

@Inject
class SearchBlock : Nav3Block<SearchBlock.Input> {

    override fun entryPointNavBarItem(): NavBarItem = SearchNavBarItem

    context(entryProviderScope: EntryProviderScope<NavKey>)
    override fun installEntries(input: Input): Nav3BlockBuilder = Nav3BlockBuilder(entryProviderScope) {
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

    class Input(
        val singleStackNavigator: SingleStackNavigator,
        val onResult: () -> Unit
    )
}
