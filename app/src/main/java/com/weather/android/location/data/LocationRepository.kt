package com.weather.android.location.data

import com.weather.android.forecast.domain.model.Coord

fun interface LocationRepository {
    suspend fun getCurrentLocation(): Coord
}