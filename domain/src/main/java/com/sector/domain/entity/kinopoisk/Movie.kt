package com.sector.domain.entity.kinopoisk

import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val name: String,
    val type: Type,
    val year: Int,
    val description: String,
    val shortDescription: String,
    val slogan: String?,
    val rating: Rating,
    val movieLength: Int,
    val ageRating: Int,
    val backdrop: Backdrop,
    val poster: Poster,
    val genres: List<Genre>,
    val countries: List<Country>,
    val persons: List<Person>
) : HomeItem {

    enum class Type {
        SERIES,
        MOVIE,
        CARTOON,
        ANIME,
        ANIMATED_SERIES
    }
}
