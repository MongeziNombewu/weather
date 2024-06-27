package com.weather.android.ui.components

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
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.weather.android.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationPermissionsRequest(modifier: Modifier = Modifier, onGranted: () -> Unit) {
    val locationPermissionState = rememberPermissionState(android.Manifest.permission.ACCESS_COARSE_LOCATION)

    if (locationPermissionState.hasPermission) {
        onGranted()
    } else {
        Column(modifier = modifier.padding(8.dp)) {
            val textToShow = if (locationPermissionState.shouldShowRationale) {
                stringResource(R.string.location_permission_rational)
            } else {
                stringResource(R.string.location_permission)
            }

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                text = textToShow
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { locationPermissionState.launchPermissionRequest() }
            ) {
                Text(text = stringResource(R.string.get_user_location))
            }
        }
    }
}