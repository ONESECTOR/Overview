package com.sector.domain.entity.kinopoisk

data class Person(
    val id: Int,
    val photo: String,
    val name: String?,
    val enName: String?,
    val description: String?,
    val profession: String?,
    val enProfession: String?
)
