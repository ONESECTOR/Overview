package com.sector.domain.entity.firebase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(
    val movieId: String? = null,
    val reviewId: String? = null,
    val movieName: String? = null,
    val userId: String? = null,
    val authorNickname: String? = null,
    val shortDescription: String? = null,
    val reviewText: String? = null,
    val sumRating: Int? = null,
    val plotRating: Int? = null,
    val actingPerformance: Int? = null,
    val direction: Int? = null,
    val artisticDesign: Int? = null,
    val editing: Int? = null,
    val musicAndSoundDesign: Int? = null,
    val originality: Int? = null,
    val emotionalImpact: Int? = null
) : Parcelable
