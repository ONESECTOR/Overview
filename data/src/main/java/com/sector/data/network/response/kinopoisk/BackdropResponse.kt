package com.sector.data.network.response.kinopoisk

import com.sector.domain.entity.kinopoisk.Backdrop
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BackdropResponse(
    @SerialName(value = "url")
    val url: String,
    @SerialName(value = "previewUrl")
    val previewUrl: String? = null
)

internal fun BackdropResponse.toDomain() = Backdrop(
    url = url,
    previewUrl = previewUrl
)