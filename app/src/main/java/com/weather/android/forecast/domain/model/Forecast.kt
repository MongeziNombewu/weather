package com.weather.android.forecast.domain.model

data class Forecast(
    val dateTime: Int,
    val main: Main,
    val weather: List<Weather>
)