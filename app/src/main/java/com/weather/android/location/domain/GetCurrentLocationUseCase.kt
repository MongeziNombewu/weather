package com.weather.android.location.domain

import com.weather.android.location.data.LocationRepository

class GetCurrentLocationUseCase(private val locationRepository: LocationRepository) {
    suspend operator fun invoke() = locationRepository.getCurrentLocation()
}