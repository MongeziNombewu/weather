package com.weather.android.location.data.remote

import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.Tasks
import com.weather.android.common.error.WeatherError
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GoogleLocationDatasourceTest {
    @Mock
    private lateinit var locationProviderClient: FusedLocationProviderClient
    private lateinit var googleLocationDatasource: GoogleLocationDatasource


    @Before
    fun setUp() {
        googleLocationDatasource = GoogleLocationDatasource(locationProviderClient)
    }

    @Test
    fun getLocation() = runTest {
        val location = mock<Location>()
        whenever(locationProviderClient.getCurrentLocation(anyInt(), any())).then { Tasks.forResult(location) }

        val result = googleLocationDatasource.getCurrentLocation()

        assertNotNull(result)
    }

    @Test(expected = WeatherError.LocationError::class)
    fun getLocationFailure() = runTest {
        whenever(locationProviderClient.getCurrentLocation(anyInt(), any())).then { throw WeatherError.LocationError }

        googleLocationDatasource.getCurrentLocation()
    }
}