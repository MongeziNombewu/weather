package com.weather.android.forecast.data.remote

import com.weather.android.StubDataFactory
import com.weather.android.common.error.WeatherError
import com.weather.android.forecast.data.remote.service.ForecastService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyDouble
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteForecastDatasourceTest {
    @Mock
    private lateinit var forecastService: ForecastService
    private lateinit var remoteForecastDatasource: RemoteForecastDatasource


    @Before
    fun setUp() {
        remoteForecastDatasource = RemoteForecastDatasource(forecastService, "test_api_key")
    }

    @Test
    fun getForecast() = runTest {
        val mockForecast = StubDataFactory.getCurrentForecastDto()
        `when`(
            forecastService.getCurrentWeatherForecast(
                anyDouble(),
                anyDouble(),
                anyString()
            )
        ).thenReturn(mockForecast)

        val result = remoteForecastDatasource.getForecast(1.0, 1.0)

        assertEquals(mockForecast, result)
    }

    @Test
    fun getWeeklyForecast() = runTest {
        val mockWeeklyForecast = StubDataFactory.getWeeklyForecastDto()
        `when`(forecastService.getWeeklyWeatherForecast(anyDouble(), anyDouble(), anyString())).thenReturn(
            mockWeeklyForecast
        )

        val result = remoteForecastDatasource.getWeeklyForecast(1.0, 1.0)

        assertEquals(mockWeeklyForecast, result)
    }

    @Test(expected = WeatherError.NetworkError::class)
    fun getWeeklyForecastFailure() = runTest {
        `when`(forecastService.getWeeklyWeatherForecast(anyDouble(), anyDouble(), anyString())).thenThrow(
            RuntimeException()
        )

        remoteForecastDatasource.getWeeklyForecast(1.0, 1.0)
    }
}