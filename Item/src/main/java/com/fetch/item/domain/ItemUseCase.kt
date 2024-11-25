package com.fetch.item.domain

import com.fetch.item.data.Item
import com.fetch.item.data.ItemService

internal class ItemUseCase(private val itemService: ItemService) {

    suspend fun getAllItems(): List<Item> = itemService.getAllItems()

    fun groupByListId(items: List<Item>): Map<Int, List<Item>> = items.groupBy { it.listId }

    fun sortByListIdAndName(groupedItems: Map<Int, List<Item>>): Map<Int, List<Item>> =
        groupedItems.entries.sortedBy { it.key }
            .associate { it.key to it.value }
            .mapValues { entry -> entry.value.sortedBy { it.name } }

    fun filterOutNullOrBlankNames(groupedItems: Map<Int, List<Item>>): Map<Int, List<Item>> =
        groupedItems.mapValues { entry ->
            entry.value.filter { it.name?.isNotBlank() == true }
        }
}
