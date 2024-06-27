package com.weather.android.location.data.remote

import com.weather.android.forecast.domain.model.Coord
import com.weather.android.location.data.LocationDatasource
import com.weather.android.location.data.LocationRepository
import com.weather.android.location.data.mapper.toDomain

class GoogleLocationRepository(private val locationDatasource: LocationDatasource) : LocationRepository {
    override suspend fun getCurrentLocation(): Coord {
        return locationDatasource.getCurrentLocation().toDomain()
    }
}