package com.weather.android.forecast.domain.model

enum class WeatherType(val weather: String) {
    CLEAR("Clear"),
    CLOUDS("Clouds"),
    RAIN("Rain"),
    SNOW("Snow"),
    THUNDERSTORM("Thunderstorm"),
    DRIZZLE("Drizzle")
}