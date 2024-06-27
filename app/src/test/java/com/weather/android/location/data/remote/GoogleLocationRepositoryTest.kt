package com.weather.android.location.data.remote

import android.location.Location
import com.weather.android.common.error.WeatherError
import com.weather.android.forecast.domain.model.Coord
import com.weather.android.location.data.LocationDatasource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GoogleLocationRepositoryTest {
    @Mock
    private lateinit var locationDatasource: LocationDatasource
    private lateinit var locationRepository: GoogleLocationRepository

    @Before
    fun setUp() {
        locationRepository = GoogleLocationRepository(locationDatasource)
    }

    @Test
    fun getLocation() = runTest {
        val location = mock<Location>()
        whenever(location.latitude).thenReturn(1.0)
        whenever(location.longitude).thenReturn(1.0)
        whenever(locationDatasource.getCurrentLocation()).then { location }

        val result: Coord = locationRepository.getCurrentLocation()

        assertEquals(location.longitude, result.longitude, 0.0)
        assertEquals(location.latitude, result.latitude, 0.0)
    }

    @Test(expected = WeatherError.LocationError::class)
    fun getLocationFailure() = runTest {
        whenever(locationDatasource.getCurrentLocation()).then {
            throw WeatherError.LocationError
        }

        locationRepository.getCurrentLocation()
    }
}