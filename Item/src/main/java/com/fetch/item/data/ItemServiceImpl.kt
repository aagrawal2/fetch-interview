package com.fetch.item.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class ItemServiceImpl(private val httpClient: HttpClient) : ItemService {

    override suspend fun getAllItems(): List<Item> =

    // TODO: Move the hardcoded url to the right place
    // TODO: Error handling and exception handling -- which is the right place to do it
    httpClient.get("https://fetch-hiring.s3.amazonaws.com/hiring.json").body()

}