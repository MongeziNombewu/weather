package com.weather.android.forecast.data.remote

import com.weather.android.common.error.WeatherError
import com.weather.android.forecast.data.ForecastDatasource
import com.weather.android.forecast.data.remote.model.CurrentForecastDto
import com.weather.android.forecast.data.remote.model.WeeklyForecastsDto
import com.weather.android.forecast.data.remote.service.ForecastService
import timber.log.Timber

class RemoteForecastDatasource(private val api: ForecastService, private val apiKey: String) : ForecastDatasource {
    override suspend fun getForecast(lat: Double, lon: Double): CurrentForecastDto {
        return api.getCurrentWeatherForecast(lat, lon, apiKey)
    }

    override suspend fun getWeeklyForecast(lat: Double, lon: Double): WeeklyForecastsDto = try {
        api.getWeeklyWeatherForecast(lat, lon, apiKey)
    } catch (e: Exception) {
        Timber.e(e)
        throw WeatherError.NetworkError
    }
}