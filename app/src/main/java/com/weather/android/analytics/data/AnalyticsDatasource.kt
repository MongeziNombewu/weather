package com.weather.android.analytics.data

fun interface AnalyticsDatasource {
    suspend fun trackEvent(eventName: String, properties: Map<String, Any>)
}