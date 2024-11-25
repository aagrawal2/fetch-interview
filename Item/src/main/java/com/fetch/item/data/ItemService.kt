package com.fetch.item.data

interface ItemService {

    suspend fun getAllItems(): List<Item>

}