package com.weather.android.forecast.data.remote

import com.weather.android.StubDataFactory
import com.weather.android.common.error.WeatherError
import com.weather.android.forecast.data.ForecastDatasource
import com.weather.android.forecast.data.toDomain
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class RemoteForecastRepositoryTest {
    @Mock
    private lateinit var remoteForecastDatasource: ForecastDatasource
    private lateinit var remoteForecastRepository: RemoteForecastRepository

    @Before
    fun setUp() {
        remoteForecastRepository = RemoteForecastRepository(remoteForecastDatasource)
    }

    @Test
    fun getForecast() = runTest {
        val mockForecast = StubDataFactory.getCurrentForecastDto()
        whenever(
            remoteForecastDatasource.getForecast(
                anyDouble(),
                anyDouble()
            )
        ).thenReturn(mockForecast)

        val result = remoteForecastRepository.getForecast(1.0, 1.0)

        assertEquals(mockForecast.toDomain(), result)
    }

    @Test
    fun getWeeklyForecast() = runTest {
        val mockWeeklyForecast = StubDataFactory.getWeeklyForecastDto()
        whenever(remoteForecastDatasource.getWeeklyForecast(anyDouble(), anyDouble())).thenReturn(
            mockWeeklyForecast
        )

        val result = remoteForecastRepository.getWeeklyForecast(1.0, 1.0)

        assertEquals(mockWeeklyForecast.toDomain(), result)
    }

    @Test(expected = WeatherError.NetworkError::class)
    fun getWeeklyForecastFailure() = runTest {
        whenever(remoteForecastDatasource.getWeeklyForecast(anyDouble(), anyDouble())).then {
            throw WeatherError.NetworkError
        }

        remoteForecastRepository.getWeeklyForecast(1.0, 1.0)
    }
}