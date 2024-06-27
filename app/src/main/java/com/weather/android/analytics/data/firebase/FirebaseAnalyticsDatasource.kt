package com.weather.android.analytics.data.firebase

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import com.weather.android.analytics.data.AnalyticsDatasource
import com.weather.android.analytics.domain.model.Analytics

class FirebaseAnalyticsDatasource(private val analytics: FirebaseAnalytics) : AnalyticsDatasource {
    override suspend fun trackEvent(eventName: String, properties: Map<String, Any>) {
        if (eventName == Analytics.Event.GET_WEATHER) {
            analytics.logEvent(FirebaseAnalytics.Event.SEARCH) {
                param(Analytics.Parameter.CITY, properties[Analytics.Parameter.CITY].toString())
                param(Analytics.Parameter.TEMPERATURE_MIN, properties[Analytics.Parameter.TEMPERATURE_MIN].toString())
                param(Analytics.Parameter.TEMPERATURE_MAX, properties[Analytics.Parameter.TEMPERATURE_MAX].toString())
                param(Analytics.Parameter.HUMIDITY, properties[Analytics.Parameter.HUMIDITY].toString())
                param(Analytics.Parameter.COUNTRY, properties[Analytics.Parameter.COUNTRY].toString())
                param(
                    Analytics.Parameter.WEATHER_CONDITION,
                    properties[Analytics.Parameter.WEATHER_CONDITION].toString()
                )
            }
        }
    }
}