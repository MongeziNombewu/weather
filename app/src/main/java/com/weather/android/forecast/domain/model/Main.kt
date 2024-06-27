package com.weather.android.forecast.domain.model

data class Main(
    val feelsLike: Double,
    val groundLevel: Int,
    val humidity: Int,
    val pressure: Int,
    val seaLevel: Int,
    val temp: Double,
    val tempMax: Double,
    val tempMin: Double
)