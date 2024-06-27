package com.weather.android.forecast.data.remote.model

import com.squareup.moshi.Json

data class MainDto(
    @Json(name = "feels_like") val feelsLike: Double,
    @Json(name = "grnd_level") val groundLevel: Int,
    val humidity: Int,
    val pressure: Int,
    @Json(name = "sea_level") val seaLevel: Int,
    val temp: Double,
    @Json(name = "temp_max") val tempMax: Double,
    @Json(name = "temp_min") val tempMin: Double
)