package com.weather.android.analytics.data.firebase

import com.google.firebase.analytics.FirebaseAnalytics
import com.weather.android.StubEventDataFactory
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verifyNoInteractions


@RunWith(MockitoJUnitRunner::class)
class FirebaseAnalyticsDatasourceTest {
    @Mock
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource


    @Before
    fun setUp() {
        firebaseAnalyticsDatasource = FirebaseAnalyticsDatasource(firebaseAnalytics)
    }

    @Test
    fun doNotTrackInvalidEvent() = runTest {
        val mockPayload = StubEventDataFactory.getEventPayload()
        val invalidEvent = "INVALID_EVENT"

        firebaseAnalyticsDatasource.trackEvent(invalidEvent, mockPayload)

        verifyNoInteractions(firebaseAnalytics)
    }
}