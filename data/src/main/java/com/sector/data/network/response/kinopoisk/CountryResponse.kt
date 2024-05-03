package com.sector.data.network.response.kinopoisk

import com.sector.domain.entity.kinopoisk.Country
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CountryResponse(
    @SerialName(value = "name")
    val name: String
)

internal fun CountryResponse.toDomain() = Country(
    name = name
)

internal fun List<CountryResponse>.toDomain() = map {
    Country(
        name = it.name
    )
}


