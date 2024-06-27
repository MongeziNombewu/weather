package com.weather.android

import com.weather.android.forecast.di.forecastModule
import com.weather.android.location.di.locationModule
import com.weather.android.ui.di.appModule
import org.junit.Test
import org.koin.android.test.verify.androidVerify
import org.koin.core.annotation.KoinExperimentalAPI

class CheckModulesTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun `test DI modules`() {
        appModule.androidVerify(
            extraTypes = listOf(
                com.weather.android.forecast.domain.use_case.GetWeeklyForecastUseCase::class,
                com.weather.android.location.domain.GetCurrentLocationUseCase::class
            )
        )
        locationModule.androidVerify()
        forecastModule.androidVerify(extraTypes = listOf(okhttp3.logging.HttpLoggingInterceptor.Logger::class))
    }
}