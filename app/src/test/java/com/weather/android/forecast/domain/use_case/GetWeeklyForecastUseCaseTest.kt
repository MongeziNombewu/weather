package com.weather.android.forecast.domain.use_case

import com.weather.android.StubDataFactory
import com.weather.android.common.error.WeatherError
import com.weather.android.forecast.data.ForecastRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyDouble
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetWeeklyForecastUseCaseTest {
    @Mock
    private lateinit var repository: ForecastRepository
    private lateinit var getWeeklyForecastUseCase: GetWeeklyForecastUseCase

    @Before
    fun setUp() {
        getWeeklyForecastUseCase = GetWeeklyForecastUseCase(repository)
    }

    @Test
    fun getWeeklyForecast() = runTest {
        val weeklyForecast = StubDataFactory.getWeeklyForecast()
        whenever(repository.getWeeklyForecast(anyDouble(), anyDouble())).thenReturn(weeklyForecast)

        val result = getWeeklyForecastUseCase(1.0, 1.0)

        assertEquals(weeklyForecast.city, result.city)
        assertTrue(weeklyForecast.list[0] == result.list[0])
    }

    @Test
    fun getWeeklyForecastIsCleansed() = runTest {
        val weeklyForecast = StubDataFactory.getWeeklyForecast()
        whenever(repository.getWeeklyForecast(anyDouble(), anyDouble())).thenReturn(weeklyForecast)

        val result = getWeeklyForecastUseCase(1.0, 1.0)

        assertTrue(result.list.size < weeklyForecast.list.size)
        assertTrue(result.list.size == 2)
    }

    @Test(expected = WeatherError.NetworkError::class)
    fun getWeeklyForecastFailure() = runTest {
        whenever(repository.getWeeklyForecast(anyDouble(), anyDouble())).then {
            throw WeatherError.NetworkError
        }

        getWeeklyForecastUseCase(1.0, 1.0)
    }
}