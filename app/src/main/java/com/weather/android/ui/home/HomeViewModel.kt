package com.weather.android.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.android.common.error.WeatherError
import com.weather.android.forecast.domain.use_case.GetWeeklyForecastUseCase
import com.weather.android.location.domain.GetCurrentLocationUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

private const val KEY_LATITUDE = "key_latitude"
private const val KEY_LONGITUDE = "key_longitude"

class HomeViewModel(
    private val getWeeklyForecastUseCase: GetWeeklyForecastUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    private val latitude: Double? = savedStateHandle[KEY_LATITUDE]
    private val longitude: Double? = savedStateHandle[KEY_LONGITUDE]

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
                _uiState.update { HomeUiState.Success(result) }

                savedStateHandle[KEY_LATITUDE] = location.latitude
                savedStateHandle[KEY_LONGITUDE] = location.longitude
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
}