package com.weather.android.forecast.domain.model

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)