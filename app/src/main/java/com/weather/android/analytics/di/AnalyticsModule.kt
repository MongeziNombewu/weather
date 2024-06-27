package com.weather.android.analytics.di

import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import com.weather.android.analytics.data.AnalyticsDatasource
import com.weather.android.analytics.data.AnalyticsRepository
import com.weather.android.analytics.data.firebase.FirebaseAnalyticsDatasource
import com.weather.android.analytics.data.firebase.FirebaseAnalyticsRepository
import com.weather.android.analytics.domain.use_case.TrackAnalyticsEventUseCase
import org.koin.dsl.module

val analyticsModule = module {
    factory { Firebase.analytics }
    factory<AnalyticsDatasource> { FirebaseAnalyticsDatasource(get()) }
    single<AnalyticsRepository> { FirebaseAnalyticsRepository(get()) }
    factory { TrackAnalyticsEventUseCase(get()) }
}