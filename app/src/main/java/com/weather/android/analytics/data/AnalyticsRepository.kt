package com.weather.android.analytics.data

fun interface AnalyticsRepository {
    suspend fun trackEvent(eventName: String, properties: Map<String, Any>)
}