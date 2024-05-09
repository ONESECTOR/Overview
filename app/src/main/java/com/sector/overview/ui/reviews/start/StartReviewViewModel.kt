package com.sector.overview.ui.reviews.start

import com.sector.domain.entity.kinopoisk.Movie
import com.sector.ui.viewmodel.BaseViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class StartReviewViewModel(
    private val movie: Movie
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

}