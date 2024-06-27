package com.weather.android.forecast.data

import com.weather.android.forecast.data.remote.model.CurrentForecastDto
import com.weather.android.forecast.data.remote.model.WeeklyForecastsDto

interface ForecastDatasource {
    suspend fun getForecast(lat: Double, lon: Double): CurrentForecastDto

    suspend fun getWeeklyForecast(lat: Double, lon: Double): WeeklyForecastsDto
}