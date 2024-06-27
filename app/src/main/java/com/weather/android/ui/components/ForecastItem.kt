package com.weather.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weather.android.R
import com.weather.android.common.niceFormat
import com.weather.android.common.util.getDayOfWeek
import com.weather.android.forecast.domain.model.Forecast
import com.weather.android.forecast.domain.model.Main
import com.weather.android.forecast.domain.model.Weather
import com.weather.android.forecast.domain.model.WeatherType
import com.weather.android.ui.theme.WeatherTheme

@Composable
fun ForecastItem(forecast: Forecast, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = getDayOfWeek(forecast.dateTime),
            modifier = Modifier.weight(1f, true),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onTertiary,
        )
        ForecastItemImage(
            weather = forecast.weather.first(),
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .weight(1f, true)
        )
        Text(
            text = stringResource(id = R.string.temperature, forecast.main.temp.niceFormat()),
            modifier = Modifier.weight(1f, true),
            textAlign = TextAlign.End, // Align text to the end
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onTertiary,
        )
    }
}

@Composable
fun ForecastItemImage(weather: Weather, modifier: Modifier = Modifier) {
    val drawableId = when (weather.main) {
        WeatherType.CLOUDS.weather -> {
            R.drawable.partly_sunny
        }

        WeatherType.RAIN.weather, WeatherType.THUNDERSTORM.weather -> {
            R.drawable.rain
        }

        else -> {
            R.drawable.clear
        }
    }

    Image(modifier = modifier, painter = painterResource(id = drawableId), contentDescription = "")
}

@Preview
@Composable
private fun ForecastItemPreview() {
    WeatherTheme {
        ForecastItem(
            Forecast(
                dateTime = 1719360000,
                main = Main(
                    temp = 20.0,
                    feelsLike = 20.0,
                    humidity = 20,
                    pressure = 20,
                    tempMax = 20.0,
                    tempMin = 20.0,
                    seaLevel = 20,
                    groundLevel = 20
                ),
                weather = listOf(Weather(description = "Cloudy", icon = "04d", id = 800, main = "Clouds"))
            )
        )
    }
}