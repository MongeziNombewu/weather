package com.weather.android.forecast.data.remote.model

import com.squareup.moshi.Json

data class Rain(
    @Json(name = "1h") val pastHour: Double = 0.0,
    @Json(name = "3h") val past3Hour: Double = 0.0
)