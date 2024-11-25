package com.fetch.hire

import android.app.Application
import com.fetch.di.networkModule
import com.fetch.item.di.itemModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FetchApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FetchApplication)
            modules(listOf(networkModule, itemModule))
        }
    }
}