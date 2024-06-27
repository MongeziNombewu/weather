package com.weather.android.forecast.data.remote.model

import com.squareup.moshi.Json

data class CurrentForecastDto(
    val clouds: Clouds,
    @Json(name = "coord") val coordinates: CoordDto = CoordDto(0.0, 0.0),
    @Json(name = "dt") val dateTime: Int,
    val id: Int,
    val main: MainDto,
    val name: String,
    val rain: Rain,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherDto>,
    val wind: Wind
)