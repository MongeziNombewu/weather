package com.weather.android.forecast.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.weather.android.BuildConfig
import com.weather.android.forecast.data.ForecastDatasource
import com.weather.android.forecast.data.ForecastRepository
import com.weather.android.forecast.data.remote.RemoteForecastDatasource
import com.weather.android.forecast.data.remote.RemoteForecastRepository
import com.weather.android.forecast.data.remote.service.ForecastService
import com.weather.android.forecast.domain.use_case.GetWeeklyForecastUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val forecastModule = module {
    factory {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    factory {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(ForecastService::class.java) }

    single<ForecastDatasource> { RemoteForecastDatasource(get(), BuildConfig.WEATHER_API_KEY) }

    single<ForecastRepository> { RemoteForecastRepository(get()) }

    single { GetWeeklyForecastUseCase(get()) }
}
