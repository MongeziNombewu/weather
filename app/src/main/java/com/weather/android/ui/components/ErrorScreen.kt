package com.weather.android.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weather.android.R
import com.weather.android.common.error.WeatherError
import com.weather.android.ui.theme.WeatherTheme

@Composable
fun ErrorScreen(error: WeatherError, modifier: Modifier = Modifier, onRetry: () -> Unit = {}) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp)
        ) {
            val message = when (error) {
                WeatherError.NetworkError -> {
                    stringResource(R.string.error_internet_connection)
                }

                WeatherError.LocationError -> {
                    stringResource(R.string.error_location_settings)
                }

                WeatherError.UnknownError -> {
                    stringResource(R.string.error_unknown)
                }
            }

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                text = message
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onRetry
            ) {
                Text(text = stringResource(R.string.retry))
            }
        }
    }
}

@Preview
@Composable
private fun ErrorPreview() {
    WeatherTheme {
        ErrorScreen(error = WeatherError.NetworkError)
    }
}