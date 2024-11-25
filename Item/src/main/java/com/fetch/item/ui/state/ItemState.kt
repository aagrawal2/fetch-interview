package com.fetch.item.ui.state

import com.fetch.item.data.Item

internal sealed class ItemState {
    data object Loading : ItemState()
    data object Error : ItemState()
    data class Success(val groupedItems: Map<Int, List<Item>>) : ItemState()
}
