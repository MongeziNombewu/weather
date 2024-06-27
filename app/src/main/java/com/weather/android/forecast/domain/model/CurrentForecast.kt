package com.weather.android.forecast.domain.model

data class CurrentForecast(
    val dateTime: Int,
    val id: Int,
    val name: String,
    val timezone: Int,
    val weather: List<Weather>
)