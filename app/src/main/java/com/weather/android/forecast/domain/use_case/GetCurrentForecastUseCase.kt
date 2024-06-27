package com.weather.android.forecast.domain.use_case

import com.weather.android.forecast.data.ForecastRepository
import com.weather.android.forecast.domain.model.CurrentForecast

class GetCurrentForecastUseCase(private val repository: ForecastRepository) {
    suspend operator fun invoke(lat: Double, lon: Double): CurrentForecast = repository.getForecast(lat, lon)
}