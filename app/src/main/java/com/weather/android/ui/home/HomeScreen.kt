package com.weather.android.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.weather.android.R
import com.weather.android.forecast.domain.model.City
import com.weather.android.forecast.domain.model.Coord
import com.weather.android.forecast.domain.model.Forecast
import com.weather.android.forecast.domain.model.Main
import com.weather.android.forecast.domain.model.Weather
import com.weather.android.forecast.domain.model.WeatherType
import com.weather.android.forecast.domain.model.WeeklyForecasts
import com.weather.android.ui.components.ErrorScreen
import com.weather.android.ui.components.ForecastHeader
import com.weather.android.ui.components.ForecastItem
import com.weather.android.ui.components.LocationPermissionsRequest
import com.weather.android.ui.components.ProgressIndicator
import com.weather.android.ui.theme.WeatherTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = koinViewModel()) {
    val homeUiState: HomeUiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (homeUiState) {
        is HomeUiState.Loading -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                ProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 24.dp)
                )
                LocationPermissionsRequest(modifier = Modifier.align(Alignment.Center)) {
                    viewModel.loadForecast()
                }
            }
        }

        is HomeUiState.Success -> HomeContent(
            forecast = (homeUiState as HomeUiState.Success).data,
            modifier = modifier.fillMaxSize()
        )

        is HomeUiState.Error -> {
            ErrorScreen(
                error = (homeUiState as HomeUiState.Error).error,
                modifier = modifier.fillMaxSize(),
                onRetry = { viewModel.loadForecast() }
            )
        }
    }
}

@Composable
fun HomeContent(forecast: WeeklyForecasts, modifier: Modifier = Modifier) {
    val todaysForecast = forecast.list.first()
    val background = when (todaysForecast.weather.first().main) {
        WeatherType.CLOUDS.weather -> R.color.cloudy
        WeatherType.RAIN.weather, WeatherType.THUNDERSTORM.weather -> R.color.rainy
        else -> R.color.sunny
    }

    Column(modifier = modifier.background(color = colorResource(id = background))) {
        ForecastHeader(forecast = todaysForecast, forecast.city)
        HorizontalDivider(thickness = 1.dp)
        val remainingForecasts = forecast.list.drop(1)
        Surface(color = colorResource(id = background)) {
            LazyColumn {
                items(count = remainingForecasts.size) { index ->
                    ForecastItem(remainingForecasts[index])
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeContentPreview() {
    WeatherTheme {
        HomeContent(
            forecast = WeeklyForecasts(
                listOf(
                    Forecast(
                        dateTime = 1630000000,
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
                    ),
                    Forecast(
                        dateTime = 1719511200,
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
                    ),
                    Forecast(
                        dateTime = 1619360000,
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
                ), city = City(
                    id = 1, name = "London", country = "UK", coord = Coord(0.0, 0.0),
                    population = 5998,
                    sunrise = 1930,
                    sunset = 2876,
                    timezone = 7594
                )
            )
        )
    }
}
