package com.sector.data.network.response.kinopoisk

import com.sector.domain.entity.kinopoisk.Rating
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RatingResponse(
    @SerialName(value = "kp")
    val kp: Double,
    @SerialName(value = "imdb")
    val imdb: Double,
    @SerialName(value = "filmCritics")
    val filmCritics: Double? = null,
    @SerialName(value = "russianFilmCritics")
    val russianFilmCritics: Double? = null,
    @SerialName(value = "await")
    val await: Int? = null
)

internal fun RatingResponse.toDomain() = Rating(
    kp = kp,
    imdb = imdb,
    filmCritics = filmCritics,
    russianFilmCritics = russianFilmCritics,
    await = await
)