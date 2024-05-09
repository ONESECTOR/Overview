package com.sector.domain.entity.kinopoisk

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
    val kp: Double,
    val imdb: Double,
    val filmCritics: Double?,
    val russianFilmCritics: Double?,
    val await: Int?
) : Parcelable
