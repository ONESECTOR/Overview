package com.sector.data.network.response.kinopoisk

import com.sector.domain.entity.kinopoisk.Poster
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PosterResponse(
    @SerialName(value = "url")
    val url: String,
    @SerialName(value = "previewUrl")
    val previewUrl: String? = null
)

internal fun PosterResponse.toDomain() = Poster(
    url = url,
    previewUrl = previewUrl
)
