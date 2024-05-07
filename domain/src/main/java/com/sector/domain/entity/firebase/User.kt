package com.sector.domain.entity.firebase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val email: String? = null,
    val password: String? = null,
    val nickname: String? = null
): Parcelable {

    fun toMap(): Map<String, Any?> {
        return mapOf(
            "email" to email,
            "password" to password,
            "nickname" to nickname
        )
    }
}
