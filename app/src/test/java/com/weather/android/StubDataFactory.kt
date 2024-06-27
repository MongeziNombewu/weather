package com.weather.android

import android.location.Location
import com.weather.android.forecast.data.remote.model.CityDto
import com.weather.android.forecast.data.remote.model.Clouds
import com.weather.android.forecast.data.remote.model.CoordDto
import com.weather.android.forecast.data.remote.model.CurrentForecastDto
import com.weather.android.forecast.data.remote.model.ForecastDto
import com.weather.android.forecast.data.remote.model.MainDto
import com.weather.android.forecast.data.remote.model.Rain
import com.weather.android.forecast.data.remote.model.WeatherDto
import com.weather.android.forecast.data.remote.model.WeeklyForecastsDto
import com.weather.android.forecast.data.remote.model.Wind
import com.weather.android.forecast.data.toDomain
import com.weather.android.location.data.mapper.toDomain

object StubDataFactory {
    fun getCurrentForecastDto(): CurrentForecastDto {
        return CurrentForecastDto(
            clouds = Clouds(all = 8186),
            coordinates = CoordDto(latitude = 28.29, longitude = 30.31),
            dateTime = 7719,
            id = 5579,
            main = MainDto(
                feelsLike = 32.33,
                groundLevel = 6883,
                humidity = 2173,
                pressure = 3346,
                seaLevel = 3044,
                temp = 34.35,
                tempMax = 36.37,
                tempMin = 38.39
            ),
            name = "Bertha Burton",
            rain = Rain(pastHour = 40.41, past3Hour = 42.43),
            timezone = 8106,
            visibility = 5183,
            weather = listOf(),
            wind = Wind(degrees = 5770, gust = 44.45, speed = 46.47)
        )
    }

    fun getWeeklyForecastDto(): WeeklyForecastsDto {
        return WeeklyForecastsDto(
            cod = 3433, message = 5905, cnt = 3750, list = getForecastDtoList(), city = CityDto(
                coordinates = CoordDto(
                    latitude = 52.53,
                    longitude = 54.55
                ),
                country = "Mauritius",
                id = 2405,
                name = "Ronda Joseph",
                population = 8090,
                sunrise = 5685,
                sunset = 5341,
                timezone = 7006
            )
        )
    }

    private fun getForecastDto(): ForecastDto {
        return ForecastDto(
            clouds = Clouds(all = 7416),
            dateTime = 1718963617,
            main = MainDto(
                feelsLike = 16.17,
                groundLevel = 4997,
                humidity = 4996,
                pressure = 9174,
                seaLevel = 3649,
                temp = 18.19,
                tempMax = 20.21,
                tempMin = 22.23
            ),
            rain = Rain(pastHour = 24.25, past3Hour = 26.27),
            visibility = 7737,
            weather = listOf(getWeatherDto()),
            wind = Wind(degrees = 2047, gust = 28.29, speed = 30.31)
        )
    }

    private fun getWeatherDto() = WeatherDto(
        description = "vix", icon = "oratio", id = 9729, main = "quem"

    )

    private fun getForecastDtoList(): List<ForecastDto> {
        return listOf(
            getForecastDto(), //9AM 21st
            getForecastDto().copy(dateTime = 1718974417), //12PM 21st
            getForecastDto().copy(dateTime = 1719003217), //8PM 21st
            getForecastDto().copy(dateTime = 1719050017), //9AM 22nd
            getForecastDto().copy(dateTime = 1719060817), //12PM 22nd
        )
    }

    fun getWeeklyForecast() = getWeeklyForecastDto().toDomain()

    fun getLocation(): Location {
        return Location("").apply {
            latitude = 1.0
            longitude = 1.0
        }
    }

    fun getCoord() = getLocation().toDomain()
}