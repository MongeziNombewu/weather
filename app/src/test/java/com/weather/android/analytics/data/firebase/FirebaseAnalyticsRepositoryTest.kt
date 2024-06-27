package com.weather.android.analytics.data.firebase

import com.weather.android.StubEventDataFactory
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doThrow
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class FirebaseAnalyticsRepositoryTest {
    @Mock
    private lateinit var firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource
    private lateinit var firebaseAnalyticsRepository: FirebaseAnalyticsRepository

    @Before
    fun setUp() {
        firebaseAnalyticsRepository = FirebaseAnalyticsRepository(firebaseAnalyticsDatasource)
    }

    @Test
    fun trackEvent() = runTest {
        val event = StubEventDataFactory.getEventPayload()

        firebaseAnalyticsRepository.trackEvent("test_event", event)

        verify(firebaseAnalyticsDatasource).trackEvent("test_event", event)
    }

    @Test
    fun verifyNoException() = runTest {
        val event = StubEventDataFactory.getEventPayload()

        doThrow(RuntimeException())
            .`when`(firebaseAnalyticsDatasource).trackEvent("test_event", event)

        firebaseAnalyticsRepository.trackEvent("test_event", event)
    }
}