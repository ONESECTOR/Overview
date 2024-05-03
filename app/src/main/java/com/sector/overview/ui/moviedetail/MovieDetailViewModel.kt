package com.sector.overview.ui.moviedetail

import com.sector.ui.viewmodel.BaseViewModel

internal class MovieDetailViewModel(

): BaseViewModel<MovieDetailViewState, MovieDetailSideEffect>(MovieDetailViewState()) {

    init {

    }
}

internal data class MovieDetailViewState(
    val name: String = ""
)

internal sealed class MovieDetailSideEffect {

}