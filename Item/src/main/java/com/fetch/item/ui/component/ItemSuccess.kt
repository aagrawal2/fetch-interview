package com.fetch.item.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.fetch.item.R
import com.fetch.item.data.Item
import com.fetch.item.ui.viewmodel.ItemViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ItemSuccess(
    modifier: Modifier = Modifier,
    groupedItems: Map<Int, List<Item>>
) {
    Scaffold(modifier = modifier.fillMaxSize()) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = dimensionResource(com.fetch.core.ui.R.dimen.spacing_large))
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var localStateListId by remember { mutableIntStateOf(groupedItems.keys.first()) }

            GroupHeader(listId = localStateListId)

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(com.fetch.core.ui.R.dimen.spacing_medium))
            ) {
                groupedItems.forEach { (listId, items) ->
                    stickyHeader {
                        localStateListId = listId
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = dimensionResource(com.fetch.core.ui.R.dimen.thickness_small)
                        )
                    }
                    items(items.size) { index ->
                        Item(
                            modifier = if (index == 0) modifier.padding(top = dimensionResource(com.fetch.core.ui.R.dimen.spacing_medium)) else modifier,
                            item = items[index]
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GroupHeader(
    modifier: Modifier = Modifier,
    listId: Int
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopHeader(listId = listId)
        ItemHeader(modifier = Modifier.padding(top = dimensionResource(com.fetch.core.ui.R.dimen.spacing_large)))
    }
}

@Composable
private fun TopHeader(
    modifier: Modifier = Modifier,
    listId: Int
) {
    val viewModel = koinViewModel<ItemViewModel>()
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier,
            onClick = {
                viewModel.sortItems()
            }
        ) {
            Text(text = stringResource(R.string.item_sort))
        }
        HeaderListID(listId = listId)
        Button(
            modifier = Modifier,
            onClick = {
                viewModel.filterItems()
            }
        ) {
            Text(text = stringResource(R.string.item_filter))
        }
    }
}

@Composable
private fun HeaderListID(
    modifier: Modifier = Modifier,
    listId: Int
) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.item_list_id, listId),
        fontSize = 30.sp
    )
}

@Composable
private fun ItemHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.width(dimensionResource(com.fetch.core.ui.R.dimen.width_small)),
            text = stringResource(R.string.item_id),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(dimensionResource(com.fetch.core.ui.R.dimen.spacing_large)))
        Text(
            modifier = Modifier.width(dimensionResource(com.fetch.core.ui.R.dimen.width_small)),
            text = stringResource(R.string.item_name),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun Item(
    modifier: Modifier = Modifier,
    item: Item
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.width(dimensionResource(com.fetch.core.ui.R.dimen.width_small)),
            text = item.id.toString(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(dimensionResource(com.fetch.core.ui.R.dimen.spacing_large)))
        Text(
            modifier = Modifier.width(dimensionResource(com.fetch.core.ui.R.dimen.width_small)),
            text = item.name ?: "null", textAlign = TextAlign.Start
        )
    }

}