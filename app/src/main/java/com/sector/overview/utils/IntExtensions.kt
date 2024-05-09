package com.sector.overview.utils

fun Int.formatMovieLength(): String {
    val hours = this / 60
    val remainingMinutes = this % 60

    return when {
        hours > 0 && remainingMinutes > 0 -> "$hours ч $remainingMinutes мин"
        hours > 0 -> "$hours ч"
        remainingMinutes > 0 -> "$remainingMinutes мин"
        else -> "0 мин"
    }
}