package com.sector.data.network

import com.sector.data.network.response.kinopoisk.MovieResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ResponseDataWrapper(
    @SerialName(value = "docs")
    val docs: List<MovieResponse>,
    @SerialName(value = "total")
    val total: Int,
    @SerialName(value = "limit")
    val limit: Int,
    @SerialName(value = "page")
    val page: Int,
    @SerialName(value = "pages")
    val pages: Int
)
