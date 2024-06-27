package com.weather.android.common

fun Double.niceFormat(): String {
    return this.toInt().toString()
}