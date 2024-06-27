package com.weather.android.forecast.domain.model

data class WeeklyForecasts(
    val list: List<Forecast>,
    val city: City
)