package com.weather.android.common.error

sealed class WeatherError : Exception() {
    data object NetworkError : WeatherError()
    data object LocationError : WeatherError()
    data object UnknownError : WeatherError()
}