package com.sector.domain.entity.kinopoisk

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Backdrop(
    val url: String,
    val previewUrl: String?
) : Parcelable
