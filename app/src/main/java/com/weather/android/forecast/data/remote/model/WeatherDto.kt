package com.weather.android.forecast.data.remote.model

data class WeatherDto(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)