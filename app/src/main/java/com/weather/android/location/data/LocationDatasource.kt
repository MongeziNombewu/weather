package com.weather.android.location.data

import android.location.Location

fun interface LocationDatasource {
    suspend fun getCurrentLocation(): Location
}