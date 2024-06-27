package com.weather.android.forecast.data

import com.weather.android.forecast.data.remote.model.CityDto
import com.weather.android.forecast.data.remote.model.CoordDto
import com.weather.android.forecast.data.remote.model.CurrentForecastDto
import com.weather.android.forecast.data.remote.model.ForecastDto
import com.weather.android.forecast.data.remote.model.MainDto
import com.weather.android.forecast.data.remote.model.WeatherDto
import com.weather.android.forecast.data.remote.model.WeeklyForecastsDto
import com.weather.android.forecast.domain.model.City
import com.weather.android.forecast.domain.model.Coord
import com.weather.android.forecast.domain.model.CurrentForecast
import com.weather.android.forecast.domain.model.Forecast
import com.weather.android.forecast.domain.model.Main
import com.weather.android.forecast.domain.model.Weather
import com.weather.android.forecast.domain.model.WeeklyForecasts

internal fun CurrentForecastDto.toDomain(): CurrentForecast {
    return CurrentForecast(
        dateTime = dateTime,
        id = id,
        name = name,
        timezone = timezone,
        weather = weather.map { it.toDomain() }
    )
}

internal fun WeatherDto.toDomain(): Weather {
    return Weather(
        description = description,
        icon = icon,
        id = id,
        main = main
    )
}

internal fun MainDto.toDomain(): Main {
    return Main(
        feelsLike = feelsLike,
        groundLevel = groundLevel,
        humidity = humidity,
        pressure = pressure,
        seaLevel = seaLevel,
        temp = temp,
        tempMax = tempMax,
        tempMin = tempMin
    )
}

internal fun ForecastDto.toDomain(): Forecast {
    return Forecast(
        dateTime = dateTime,
        main = main.toDomain(),
        weather = weather.map { it.toDomain() }
    )
}

internal fun WeeklyForecastsDto.toDomain(): WeeklyForecasts {
    return WeeklyForecasts(
        city = city.toDomain(),
        list = list.map { it.toDomain() }
    )
}

internal fun CoordDto.toDomain(): Coord {
    return Coord(
        latitude = latitude,
        longitude = longitude
    )
}

internal fun CityDto.toDomain(): City {
    return City(
        id = id,
        country = country,
        name = name,
        population = population,
        sunrise = sunrise,
        sunset = sunset,
        timezone = timezone,
        coord = coordinates.toDomain()
    )
}