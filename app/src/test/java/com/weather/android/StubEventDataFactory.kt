package com.weather.android

import com.weather.android.analytics.domain.model.Analytics

object StubEventDataFactory {
    fun getEventPayload(): Map<String, Any> {
        return mapOf(
            Analytics.Parameter.CITY to "Valhalla",
            Analytics.Parameter.WEATHER_CONDITION to "Sunny",
            Analytics.Parameter.TEMPERATURE_MAX to 35,
            Analytics.Parameter.TEMPERATURE_MIN to 30,
            Analytics.Parameter.HUMIDITY to 100,
            Analytics.Parameter.COUNTRY to "Vinland"
        )
    }
}