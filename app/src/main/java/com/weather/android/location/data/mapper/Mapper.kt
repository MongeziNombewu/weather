package com.weather.android.location.data.mapper

import android.location.Location
import com.weather.android.forecast.domain.model.Coord

fun Location.toDomain(): Coord {
    return Coord(latitude, longitude)
}