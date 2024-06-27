package com.weather.android.forecast.data.remote.model

class WeeklyForecastsDto(
    val cod: Int,
    val message: Int,
    val cnt: Int,
    val list: List<ForecastDto>,
    val city: CityDto
)