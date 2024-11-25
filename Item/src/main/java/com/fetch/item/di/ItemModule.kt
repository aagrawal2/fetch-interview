package com.fetch.item.di

import com.fetch.item.data.ItemService
import com.fetch.item.data.ItemServiceImpl
import com.fetch.item.domain.ItemUseCase
import com.fetch.item.ui.viewmodel.ItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val itemModule = module {
    factory<ItemService> { ItemServiceImpl(get()) }
    factory { ItemUseCase(get()) }
    viewModel { ItemViewModel(get()) }
}