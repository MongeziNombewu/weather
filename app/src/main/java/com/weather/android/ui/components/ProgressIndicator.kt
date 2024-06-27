package com.weather.android.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.weather.android.ui.theme.WeatherTheme

@Composable
fun ProgressIndicator(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
    }
}

@Preview
@Composable
fun ProgressIndicatorPreview() {
    WeatherTheme {
        ProgressIndicator()
    }
}