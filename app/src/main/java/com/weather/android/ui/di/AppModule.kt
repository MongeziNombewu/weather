package com.weather.android.ui.di

import com.weather.android.ui.home.HomeViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    viewModelOf(::HomeViewModel)

    factory<CoroutineDispatcher> { Dispatchers.IO }
}