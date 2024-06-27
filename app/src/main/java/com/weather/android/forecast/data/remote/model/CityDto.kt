package com.weather.android.forecast.data.remote.model

import com.squareup.moshi.Json

data class CityDto(
    @Json(name = "coord") val coordinates: CoordDto,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)