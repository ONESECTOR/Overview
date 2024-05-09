package com.sector.overview.ui.reviews.detail

import com.sector.domain.entity.firebase.Review
import com.sector.ui.viewmodel.BaseViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class ReviewDetailViewModel(
    private val review: Review
): BaseViewModel<ReviewDetailViewState, ReviewDetailSideEffect>(ReviewDetailViewState()) {

    init {
        initialize()
    }

    private fun initialize() = intent {
        reduce {
            state.copy(review = review)
        }
    }
}

internal data class ReviewDetailViewState(
    val review: Review? = null
)

internal sealed class ReviewDetailSideEffect {

}