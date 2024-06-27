package com.weather.android.forecast.data.remote.service

import com.weather.android.forecast.data.remote.model.CurrentForecastDto
import com.weather.android.forecast.data.remote.model.WeeklyForecastsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    @GET("weather?units=metric")
    suspend fun getCurrentWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ): CurrentForecastDto

    @GET("forecast?units=metric")
    suspend fun getWeeklyWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ): WeeklyForecastsDto
}