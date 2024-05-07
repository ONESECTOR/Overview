package com.sector.domain.entity.firebase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val email: String? = null,
    val password: String? = null
): Parcelable
