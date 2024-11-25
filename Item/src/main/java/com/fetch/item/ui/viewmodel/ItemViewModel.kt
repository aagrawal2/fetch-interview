package com.fetch.item.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetch.item.domain.ItemUseCase
import com.fetch.item.ui.state.ItemState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class ItemViewModel(
    private val itemUseCase: ItemUseCase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    private val _items = MutableStateFlow<ItemState>(ItemState.Loading)
    val items: StateFlow<ItemState> = _items.asStateFlow()

    fun showItems() = viewModelScope.launch {
        runCatching {
            val items = withContext(ioDispatcher) {
                itemUseCase.getAllItems()
            }
            withContext(defaultDispatcher) {
                itemUseCase.groupByListId(items = items)
            }
        }.onSuccess { groupedItems ->
            _items.update { ItemState.Success(groupedItems = groupedItems) }
        }.onFailure {
            _items.update { _ ->
                ItemState.Error
            }
        }
    }

    fun sortItems() = viewModelScope.launch {
        runCatching {
            withContext(defaultDispatcher) {
                (items.value as? ItemState.Success)?.let {
                    itemUseCase.sortByListIdAndName(groupedItems = it.groupedItems)
                }
            }
        }.onSuccess { groupedItems ->
            groupedItems?.let {
                _items.update { _ -> ItemState.Success(groupedItems = it) }
            }
        }.onFailure {
            _items.update { _ ->
                ItemState.Error
            }
        }
    }

    fun filterItems() = viewModelScope.launch {
        runCatching {
            withContext(defaultDispatcher) {
                (items.value as? ItemState.Success)?.let {
                    itemUseCase.filterOutNullOrBlankNames(groupedItems = it.groupedItems)
                }
            }
        }.onSuccess { groupedItems ->
            groupedItems?.let {
                _items.update { _ -> ItemState.Success(groupedItems = it) }
            }
        }.onFailure {
            _items.update { _ ->
                ItemState.Error
            }
        }
    }
}