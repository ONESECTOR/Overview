package com.sector.overview.ui.reviews.detail

import com.sector.domain.entity.firebase.Review
import com.sector.ui.viewmodel.BaseViewModel

internal class ReviewDetailViewModel(
    private val review: Review
): BaseViewModel<ReviewDetailViewState, ReviewDetailSideEffect>(ReviewDetailViewState()) {

    init {
        initialize()
    }

    private fun initialize() {

    }
}

internal data class ReviewDetailViewState(
    val name: String = ""
)

internal sealed class ReviewDetailSideEffect {

}