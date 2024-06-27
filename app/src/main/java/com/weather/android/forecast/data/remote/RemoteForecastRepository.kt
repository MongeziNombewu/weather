package com.weather.android.forecast.data.remote

import com.weather.android.forecast.data.ForecastDatasource
import com.weather.android.forecast.data.ForecastRepository
import com.weather.android.forecast.data.toDomain
import com.weather.android.forecast.domain.model.CurrentForecast
import com.weather.android.forecast.domain.model.WeeklyForecasts

class RemoteForecastRepository(private val datasource: ForecastDatasource) : ForecastRepository {
    override suspend fun getForecast(lat: Double, lon: Double): CurrentForecast =
        datasource.getForecast(lat, lon).toDomain()

    override suspend fun getWeeklyForecast(lat: Double, lon: Double): WeeklyForecasts =
        datasource.getWeeklyForecast(lat, lon).toDomain()
}