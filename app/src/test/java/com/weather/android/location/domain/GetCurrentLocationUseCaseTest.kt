package com.weather.android.location.domain

import com.weather.android.common.error.WeatherError
import com.weather.android.forecast.domain.model.Coord
import com.weather.android.location.data.LocationRepository
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
class GetCurrentLocationUseCaseTest {
    @Mock
    private lateinit var repository: LocationRepository
    private lateinit var getCurrentLocationUseCase: GetCurrentLocationUseCase

    @Before
    fun setUp() {
        getCurrentLocationUseCase = GetCurrentLocationUseCase(repository)
    }

    @Test
    fun getLocation() = runTest {
        val coord = mock<Coord>()
        whenever(coord.latitude).thenReturn(1.0)
        whenever(coord.longitude).thenReturn(1.0)
        whenever(repository.getCurrentLocation()).thenReturn(coord)

        val result = getCurrentLocationUseCase()

        assertEquals(coord.latitude, result.latitude, 0.0)
        assertEquals(coord.longitude, result.longitude, 0.0)
    }

    @Test(expected = WeatherError.LocationError::class)
    fun getLocationFailure() = runTest {
        whenever(repository.getCurrentLocation()).then {
            throw WeatherError.LocationError
        }

        getCurrentLocationUseCase()
    }
}