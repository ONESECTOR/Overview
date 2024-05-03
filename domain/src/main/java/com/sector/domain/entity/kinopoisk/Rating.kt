package com.sector.domain.entity.kinopoisk

data class Rating(
    val kp: Double,
    val imdb: Double,
    val filmCritics: Double?,
    val russianFilmCritics: Double?,
    val await: Int?
)
