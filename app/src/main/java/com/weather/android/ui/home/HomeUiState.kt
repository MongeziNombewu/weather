package com.weather.android.ui.home

import com.weather.android.common.error.WeatherError
import com.weather.android.forecast.domain.model.WeeklyForecasts

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Error(val error: WeatherError) : HomeUiState()
    data class Success(val data: WeeklyForecasts) : HomeUiState()
}