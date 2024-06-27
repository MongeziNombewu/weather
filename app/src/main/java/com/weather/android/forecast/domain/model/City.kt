package com.weather.android.forecast.domain.model

data class City(
    val id: Int,
    val country: String,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int,
    val coord: Coord
)