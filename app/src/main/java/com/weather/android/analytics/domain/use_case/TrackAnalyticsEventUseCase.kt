package com.weather.android.analytics.domain.use_case

import com.weather.android.analytics.data.AnalyticsRepository

class TrackAnalyticsEventUseCase(private val repository: AnalyticsRepository) {
    suspend operator fun invoke(eventName: String, properties: Map<String, Any>) {
        repository.trackEvent(eventName, properties)
    }
}