package com.weather.android.location.data.remote

import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.weather.android.common.error.WeatherError
import com.weather.android.location.data.LocationDatasource
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class GoogleLocationDatasource(private val locationProviderClient: FusedLocationProviderClient) : LocationDatasource {
    override suspend fun getCurrentLocation(): Location {
        try {
            return locationProviderClient.getCurrentLocation(
                Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                CancellationTokenSource().token
            ).await()
        } catch (e: SecurityException) {
            Timber.e(e)
            throw WeatherError.LocationError
        }
    }
}