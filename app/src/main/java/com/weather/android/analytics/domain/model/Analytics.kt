package com.weather.android.analytics.domain.model

class Analytics {
    object Event {
        const val GET_WEATHER = "get_weather"
    }

    object Parameter {
        const val CITY = "city"
        const val COUNTRY = "country"
        const val HUMIDITY = "humidity"
        const val TEMPERATURE_MIN = "min_temperature"
        const val TEMPERATURE_MAX = "max_temperature"
        const val WEATHER_CONDITION = "weather_condition"
    }
}