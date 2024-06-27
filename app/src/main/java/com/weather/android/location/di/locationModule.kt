package com.weather.android.location.di

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.weather.android.location.data.LocationDatasource
import com.weather.android.location.data.LocationRepository
import com.weather.android.location.data.remote.GoogleLocationDatasource
import com.weather.android.location.data.remote.GoogleLocationRepository
import com.weather.android.location.domain.use_case.GetCurrentLocationUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val locationModule = module {
    single<LocationRepository> { GoogleLocationRepository(get()) }
    single<LocationDatasource> { GoogleLocationDatasource(get()) }
    single<FusedLocationProviderClient> { LocationServices.getFusedLocationProviderClient(androidContext()) }
    factory { GetCurrentLocationUseCase(get()) }
}