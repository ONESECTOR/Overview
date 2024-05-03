package com.sector.data.network.response.kinopoisk

import com.sector.domain.entity.kinopoisk.Person
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PersonResponse(
    @SerialName(value = "id")
    val id: Int,
    @SerialName(value = "photo")
    val photo: String,
    @SerialName(value = "name")
    val name: String? = null,
    @SerialName(value = "enName")
    val enName: String? = null,
    @SerialName(value = "description")
    val description: String? = null,
    @SerialName(value = "profession")
    val profession: String? = null,
    @SerialName(value = "enProfession")
    val enProfession: String? = null
)

internal fun PersonResponse.toDomain() = Person(
    id = id,
    photo = photo,
    name = name,
    enName = enName,
    description = description,
    profession = profession,
    enProfession = enProfession
)

internal fun List<PersonResponse>.toDomain() = map {
    Person(
        id = it.id,
        photo = it.photo,
        name = it.name,
        enName = it.enName,
        description = it.description,
        profession = it.profession,
        enProfession = it.enProfession
    )
}