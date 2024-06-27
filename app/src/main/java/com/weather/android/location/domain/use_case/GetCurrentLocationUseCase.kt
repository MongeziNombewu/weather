package com.weather.android.location.domain.use_case

import com.weather.android.location.data.LocationRepository

class GetCurrentLocationUseCase(private val locationRepository: LocationRepository) {
    suspend operator fun invoke() = locationRepository.getCurrentLocation()
}