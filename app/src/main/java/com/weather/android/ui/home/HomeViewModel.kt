package com.weather.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.android.analytics.domain.model.Analytics
import com.weather.android.analytics.domain.use_case.TrackAnalyticsEventUseCase
import com.weather.android.common.error.WeatherError
import com.weather.android.forecast.domain.model.WeeklyForecasts
import com.weather.android.forecast.domain.use_case.GetWeeklyForecastUseCase
import com.weather.android.location.domain.use_case.GetCurrentLocationUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val getWeeklyForecastUseCase: GetWeeklyForecastUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val getAnalyticsEventUseCase: TrackAnalyticsEventUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    fun loadForecast() {
        viewModelScope.launch(dispatcher) {
            _uiState.update { HomeUiState.Loading }

            try {
                val location = getCurrentLocationUseCase()
                Timber.d("Location: $location")
                val result = getWeeklyForecastUseCase(location.latitude, location.longitude)
                Timber.d("Result: $result")
                trackWeatherEvent(result)
                _uiState.update { HomeUiState.Success(result) }
            } catch (e: Exception) {
                Timber.e(e)
                if (e !is WeatherError) {
                    _uiState.update { HomeUiState.Error(WeatherError.UnknownError) }
                } else {
                    _uiState.update { HomeUiState.Error(e) }
                }
                return@launch
            }
        }
    }

    private suspend fun trackWeatherEvent(forecasts: WeeklyForecasts) {
        val currentDayForecast = forecasts.list.first()

        val properties = mapOf(
            Analytics.Parameter.CITY to forecasts.city.name,
            Analytics.Parameter.WEATHER_CONDITION to currentDayForecast.weather.first().main,
            Analytics.Parameter.TEMPERATURE_MAX to currentDayForecast.main.tempMax,
            Analytics.Parameter.TEMPERATURE_MIN to currentDayForecast.main.tempMin,
            Analytics.Parameter.HUMIDITY to currentDayForecast.main.humidity,
            Analytics.Parameter.COUNTRY to forecasts.city.country
        )

        getAnalyticsEventUseCase(Analytics.Event.GET_WEATHER, properties)
    }
}