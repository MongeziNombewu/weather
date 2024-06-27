package com.weather.android.forecast.data.remote.model

import com.squareup.moshi.Json

data class CoordDto(
    @Json(name = "lat") val latitude: Double,
    @Json(name = "lon") val longitude: Double
)