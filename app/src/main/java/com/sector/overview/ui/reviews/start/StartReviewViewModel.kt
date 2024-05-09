package com.sector.overview.ui.reviews.start

import com.sector.domain.entity.kinopoisk.Movie
import com.sector.ui.viewmodel.BaseViewModel

internal class StartReviewViewModel(
    private val movie: Movie
): BaseViewModel<StartReviewViewState, StartReviewSideEffect>(StartReviewViewState()) {

    init {
        initialize()
    }

    private fun initialize() {

    }
}

internal data class StartReviewViewState(
    val name: String = ""
)

internal sealed class StartReviewSideEffect {

}