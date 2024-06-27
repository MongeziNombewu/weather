package com.weather.android.analytics.data.firebase

import com.weather.android.analytics.data.AnalyticsDatasource
import com.weather.android.analytics.data.AnalyticsRepository
import timber.log.Timber

class FirebaseAnalyticsRepository(private val datasource: AnalyticsDatasource) : AnalyticsRepository {
    override suspend fun trackEvent(eventName: String, properties: Map<String, Any>) {
        try {
            datasource.trackEvent(eventName, properties)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}