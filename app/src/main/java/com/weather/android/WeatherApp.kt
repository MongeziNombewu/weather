package com.weather.android

import android.app.Application
import com.weather.android.forecast.di.forecastModule
import com.weather.android.location.di.locationModule
import com.weather.android.ui.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@WeatherApp)
            modules(appModule, forecastModule, locationModule)
        }
    }
}