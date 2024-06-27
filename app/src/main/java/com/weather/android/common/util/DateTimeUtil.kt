package com.weather.android.common.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Locale

fun getDayOfWeek(dateTime: Int): String {
    val instant = Instant.ofEpochSecond(dateTime.toLong())
    val dayOfWeek = instant.atZone(ZoneId.systemDefault()).dayOfWeek
    return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
}