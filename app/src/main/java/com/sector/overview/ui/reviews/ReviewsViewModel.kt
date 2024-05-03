package com.sector.overview.ui.reviews

import com.sector.ui.viewmodel.BaseViewModel

internal class ReviewsViewModel(

): BaseViewModel<ReviewsViewState, ReviewsSideEffect>(ReviewsViewState()) {

    init {

    }
}

internal data class ReviewsViewState(
    val name: String = ""
)

internal sealed class ReviewsSideEffect {

}