package com.weather.android.forecast.data.remote.model

import com.squareup.moshi.Json

data class ForecastDto(
    val clouds: Clouds,
    @Json(name = "dt") val dateTime: Int,
    val main: MainDto,
    val rain: Rain = Rain(),
    val visibility: Int = -1,
    val weather: List<WeatherDto>,
    val wind: Wind
)