package com.sector.data.network.response.kinopoisk

import com.sector.domain.entity.kinopoisk.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieResponse(
    @SerialName(value = "id")
    val id: Int,
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "type")
    val type: TypeResponse,
    @SerialName(value = "year")
    val year: Int,
    @SerialName(value = "description")
    val description: String,
    @SerialName(value = "shortDescription")
    val shortDescription: String,
    @SerialName(value = "slogan")
    val slogan: String? = null,
    @SerialName(value = "rating")
    val rating: RatingResponse,
    @SerialName(value = "movieLength")
    val movieLength: Int,
    @SerialName(value = "ageRating")
    val ageRating: Int,
    @SerialName(value = "backdrop")
    val backdrop: BackdropResponse,
    @SerialName(value = "poster")
    val poster: PosterResponse,
    @SerialName(value = "genres")
    val genres: List<GenreResponse>,
    @SerialName(value = "countries")
    val countries: List<CountryResponse>,
    @SerialName(value = "persons")
    val persons: List<PersonResponse>
)

@Serializable
internal enum class TypeResponse {
    @SerialName("tv-series")
    SERIES,
    @SerialName("movie")
    MOVIE,
    @SerialName("cartoon")
    CARTOON,
    @SerialName("anime")
    ANIME,
    @SerialName("animated-series")
    ANIMATED_SERIES
}

internal fun TypeResponse.toDomain(): Movie.Type {
    return when(this) {
        TypeResponse.SERIES -> Movie.Type.SERIES
        TypeResponse.MOVIE -> Movie.Type.MOVIE
        TypeResponse.CARTOON -> Movie.Type.CARTOON
        TypeResponse.ANIME -> Movie.Type.ANIME
        TypeResponse.ANIMATED_SERIES -> Movie.Type.ANIMATED_SERIES
    }
}

internal fun MovieResponse.toDomain() = Movie(
    id = id,
    name = name,
    type = type.toDomain(),
    year = year,
    description = description,
    shortDescription = shortDescription,
    slogan = slogan,
    rating = rating.toDomain(),
    movieLength = movieLength,
    ageRating = ageRating,
    backdrop = backdrop.toDomain(),
    poster = poster.toDomain(),
    genres = genres.toDomain(),
    countries = countries.toDomain(),
    persons = persons.toDomain(),
)

internal fun List<MovieResponse>.toDomain() = map {
    Movie(
        id = it.id,
        name = it.name,
        type = it.type.toDomain(),
        year = it.year,
        description = it.description,
        shortDescription = it.shortDescription,
        slogan = it.slogan,
        rating = it.rating.toDomain(),
        movieLength = it.movieLength,
        ageRating = it.ageRating,
        backdrop = it.backdrop.toDomain(),
        poster = it.poster.toDomain(),
        genres = it.genres.toDomain(),
        countries = it.countries.toDomain(),
        persons = it.persons.toDomain(),
    )
}
