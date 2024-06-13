package com.sector.overview.ui.reviews.start

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sector.domain.entity.firebase.Review
import com.sector.domain.entity.kinopoisk.Movie
import com.sector.overview.di.services.UserService
import com.sector.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import java.util.UUID

internal class StartReviewViewModel(
    private val movie: Movie,
    private val firestoreDatabase: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val userService: UserService
): BaseViewModel<StartReviewViewState, StartReviewSideEffect>(StartReviewViewState()) {

    init {
        initialize()
    }

    fun onChangePlotValue(value: Float) = intent {
        reduce {
            state.copy(
                plotValue = value
            )
        }
    }

    fun onChangeActingPerformanceValue(value: Float) = intent {
        reduce {
            state.copy(
                actingPerformanceValue = value
            )
        }
    }

    fun onChangeDirectionValue(value: Float) = intent {
        reduce {
            state.copy(
                directionValue = value
            )
        }
    }

    fun onChangeArtisticDesignValue(value: Float) = intent {
        reduce {
            state.copy(
                artisticDesignValue = value
            )
        }
    }

    fun onChangeEditingValue(value: Float) = intent {
        reduce {
            state.copy(
                editingValue = value
            )
        }
    }

    fun onChangeMusicAndSoundDesignValue(value: Float) = intent {
        reduce {
            state.copy(
                musicAndSoundDesignValue = value
            )
        }
    }

    fun onChangeOriginalityValue(value: Float) = intent {
        reduce {
            state.copy(
                originalityValue = value
            )
        }
    }

    fun onChangeEmotionalImpactValue(value: Float) = intent {
        reduce {
            state.copy(
                emotionalImpactValue = value
            )
        }
    }

    fun sendReview(shortDescription: String, review: String) = intent {
        firestoreDatabase.collection("reviews")
            .document(UUID.randomUUID().toString())
            .set(
                Review(
                    movieId = movie.id.toString(),
                    reviewId = UUID.randomUUID().toString(),
                    movieName = movie.name,
                    userId = firebaseAuth.currentUser?.uid,
                    authorNickname = userService.authState.value.nickname,
                    shortDescription = shortDescription,
                    reviewText = review,
                    sumRating = (
                            state.plotValue +
                                    state.actingPerformanceValue +
                                    state.directionValue +
                                    state.artisticDesignValue +
                                    state.editingValue +
                                    state.musicAndSoundDesignValue +
                                    state.originalityValue +
                                    state.emotionalImpactValue
                            ).toInt(),
                    plotRating = state.plotValue.toInt(),
                    actingPerformance = state.actingPerformanceValue.toInt(),
                    direction = state.directionValue.toInt(),
                    artisticDesign = state.artisticDesignValue.toInt(),
                    editing = state.editingValue.toInt(),
                    musicAndSoundDesign = state.musicAndSoundDesignValue.toInt(),
                    originality = state.originalityValue.toInt(),
                    emotionalImpact = state.emotionalImpactValue.toInt()
                ).toMap()
            )
            .addOnSuccessListener {
                viewModelScope.launch {
                    postSideEffect(StartReviewSideEffect.SuccessDialog)
                }
            }
            .addOnFailureListener {
                viewModelScope.launch {
                    postSideEffect(StartReviewSideEffect.Toast(message = it.localizedMessage))
                }
            }
    }

    private fun initialize() = intent {
        reduce {
            state.copy(movie = movie)
        }
    }
}

internal data class StartReviewViewState(
    val movie: Movie? = null,
    val plotValue: Float = 0f,
    val actingPerformanceValue: Float = 0f,
    val directionValue: Float = 0f,
    val artisticDesignValue: Float = 0f,
    val editingValue: Float = 0f,
    val musicAndSoundDesignValue: Float = 0f,
    val originalityValue: Float = 0f,
    val emotionalImpactValue: Float = 0f
)

internal sealed class StartReviewSideEffect {
    data class Toast(val message: String?): StartReviewSideEffect()

    data object SuccessDialog: StartReviewSideEffect()
}