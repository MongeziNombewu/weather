package com.weather.android.forecast.data

import com.weather.android.forecast.domain.model.CurrentForecast
import com.weather.android.forecast.domain.model.WeeklyForecasts

interface ForecastRepository {
    suspend fun getForecast(lat: Double, lon: Double): CurrentForecast

    suspend fun getWeeklyForecast(lat: Double, lon: Double): WeeklyForecasts
}