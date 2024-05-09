package com.sector.domain.entity.kinopoisk

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val id: Int,
    val photo: String,
    val name: String?,
    val enName: String?,
    val description: String?,
    val profession: String?,
    val enProfession: String?
) : Parcelable
