package com.weather.android.forecast.data.remote.model

import com.squareup.moshi.Json

data class Wind(
    @Json(name = "deg") val degrees: Int,
    val gust: Double,
    val speed: Double
)