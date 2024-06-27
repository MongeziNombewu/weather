package com.weather.android.forecast.domain.use_case

import com.weather.android.common.util.getDayOfWeek
import com.weather.android.forecast.data.ForecastRepository
import com.weather.android.forecast.domain.model.WeeklyForecasts
import timber.log.Timber

class GetWeeklyForecastUseCase(private val repository: ForecastRepository) {
    suspend operator fun invoke(lat: Double, lon: Double): WeeklyForecasts {
        // get the weekly forecast, but only one forecast per day
        val weeklyForecasts = repository.getWeeklyForecast(lat, lon)
        val days = mutableListOf<String>()
        val requiredForecasts = weeklyForecasts.list.filter { forecast ->
            val day = getDayOfWeek(forecast.dateTime)
            Timber.d("Day: $day from ${forecast.dateTime}")
            if (days.contains(day)) {
                false
            } else {
                days.add(day)
                true
            }
        }

        return weeklyForecasts.copy(list = requiredForecasts)
    }
}