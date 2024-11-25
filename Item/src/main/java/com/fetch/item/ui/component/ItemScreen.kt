package com.fetch.item.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fetch.core.ui.components.FetchError
import com.fetch.core.ui.components.FetchProgress
import com.fetch.item.ui.state.ItemState
import com.fetch.item.ui.viewmodel.ItemViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun ItemScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<ItemViewModel>()
    val state by viewModel.items.collectAsStateWithLifecycle()

    when (state) {
        is ItemState.Loading -> {
            viewModel.showItems()
            FetchProgress(modifier = modifier)
        }

        is ItemState.Success -> {
            ItemSuccess(
                modifier = modifier,
                groupedItems = (state as ItemState.Success).groupedItems
            )

        }

        is ItemState.Error -> {
            FetchError(modifier = modifier)
        }
    }
}