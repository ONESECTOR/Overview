package com.sector.data.network.response.kinopoisk

import com.sector.domain.entity.kinopoisk.Genre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GenreResponse(
    @SerialName(value = "name")
    val name: String
)

internal fun GenreResponse.toDomain() = Genre(
    name = name
)

internal fun List<GenreResponse>.toDomain() = map {
    Genre(
        name = it.name
    )
}
