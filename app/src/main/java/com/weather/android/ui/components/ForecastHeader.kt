package com.weather.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weather.android.R
import com.weather.android.common.niceFormat
import com.weather.android.forecast.domain.model.City
import com.weather.android.forecast.domain.model.Coord
import com.weather.android.forecast.domain.model.Forecast
import com.weather.android.forecast.domain.model.Main
import com.weather.android.forecast.domain.model.Weather
import com.weather.android.forecast.domain.model.WeatherType
import com.weather.android.ui.theme.WeatherTheme

@Composable
fun ForecastHeader(forecast: Forecast, city: City, modifier: Modifier = Modifier) {
    val weather = forecast.weather.first()
    val main = forecast.main
    Column(modifier = modifier) {
        when (weather.main) {
            WeatherType.CLOUDS.weather -> {
                ForecastContent(
                    drawable = R.drawable.forest_cloudy,
                    temperature = main.temp,
                    weather = weather.description,
                    city = city.name
                )
                ForecastFooter(
                    mainWeather = main,
                    modifier = Modifier.background(colorResource(id = R.color.cloudy))
                )
            }

            WeatherType.RAIN.weather, WeatherType.THUNDERSTORM.weather -> {
                ForecastContent(
                    drawable = R.drawable.forest_rainy,
                    temperature = main.temp,
                    weather = weather.description,
                    city = city.name
                )
                ForecastFooter(
                    mainWeather = main,
                    modifier = Modifier.background(colorResource(id = R.color.rainy))
                )
            }

            // default to sunny as we only have resources for 3 weather types
            else -> {
                ForecastContent(
                    drawable = R.drawable.forest_sunny,
                    temperature = main.temp,
                    weather = weather.description,
                    city = city.name
                )
                ForecastFooter(
                    mainWeather = main,
                    modifier = Modifier.background(colorResource(id = R.color.sunny))
                )
            }
        }
    }
}

@Composable
fun ForecastContent(drawable: Int, temperature: Double, weather: String, city: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = drawable),
            contentScale = ContentScale.FillWidth,
            contentDescription = ""
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.temperature, temperature.niceFormat()),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onTertiary,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = weather.uppercase(),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onTertiary,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = city,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onTertiary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ForecastFooter(mainWeather: Main, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        TemperatureItem(
            modifier = Modifier.padding(8.dp),
            temp = mainWeather.tempMin.niceFormat(),
            label = stringResource(id = R.string.temperature_min_label),
        )
        TemperatureItem(
            modifier = Modifier.padding(8.dp),
            temp = mainWeather.temp.niceFormat(),
            label = stringResource(id = R.string.temperature_current_label),
        )
        TemperatureItem(
            modifier = Modifier.padding(8.dp),
            temp = mainWeather.tempMax.niceFormat(),
            label = stringResource(id = R.string.temperature_max_label),
        )
    }
}

@Composable
fun TemperatureItem(temp: String, label: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(horizontal = 8.dp)) {
        Text(
            text = stringResource(id = R.string.temperature, temp),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
private fun ForecastHeaderPreview() {
    WeatherTheme {
        ForecastHeader(
            forecast = Forecast(
                dateTime = 1630000000,
                weather = listOf(
                    Weather(
                        description = "Cloudy",
                        icon = "04d",
                        id = 803,
                        main = "Clouds"
                    )
                ),
                main = Main(
                    temp = 20.0,
                    feelsLike = 20.0,
                    tempMin = 20.0,
                    tempMax = 20.0,
                    pressure = 1013,
                    humidity = 50,
                    seaLevel = 1013,
                    groundLevel = 1013
                ),
            ),
            city = City(
                name = "London",
                country = "GB",
                timezone = 0,
                id = 6797,
                population = 1783,
                sunrise = 7450,
                sunset = 8651,
                coord = Coord(latitude = 4.5, longitude = 6.7),

                )
        )
    }
}