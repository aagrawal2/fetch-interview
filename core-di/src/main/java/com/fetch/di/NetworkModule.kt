package com.fetch.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single { createHttpClient() }

    /* Logger & Analytics instance
    single { createLogger() }
    single { createAnalytics() }
    */
}

private fun createHttpClient() = HttpClient(Android) {
    install(ContentNegotiation) {
        json(json = Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
        // Customize Timeout config, logging config, etc... (if needed)
    }
}