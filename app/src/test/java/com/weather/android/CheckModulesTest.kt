package com.weather.android

import com.weather.android.analytics.di.analyticsModule
import com.weather.android.analytics.domain.use_case.TrackAnalyticsEventUseCase
import com.weather.android.forecast.di.forecastModule
import com.weather.android.forecast.domain.use_case.GetWeeklyForecastUseCase
import com.weather.android.location.di.locationModule
import com.weather.android.location.domain.use_case.GetCurrentLocationUseCase
import com.weather.android.ui.di.appModule
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test
import org.koin.android.test.verify.androidVerify
import org.koin.core.annotation.KoinExperimentalAPI

class CheckModulesTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun `test DI modules`() {
        appModule.androidVerify(
            extraTypes = listOf(
                GetWeeklyForecastUseCase::class,
                GetCurrentLocationUseCase::class,
                TrackAnalyticsEventUseCase::class
            )
        )
        locationModule.androidVerify()
        forecastModule.androidVerify(extraTypes = listOf(HttpLoggingInterceptor.Logger::class))
        analyticsModule.androidVerify()
    }
}