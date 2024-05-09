package com.sector.domain.entity.kinopoisk

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val name: String
) : Parcelable
