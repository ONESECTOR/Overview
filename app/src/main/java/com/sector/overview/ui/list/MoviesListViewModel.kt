package com.sector.overview.ui.list

import com.sector.domain.entity.kinopoisk.Movie
import com.sector.domain.usecase.kinopoisk.KinopoiskUseCase
import com.sector.ui.viewmodel.BaseViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class MoviesListViewModel(
    private val kinopoiskUseCase: KinopoiskUseCase
): BaseViewModel<MoviesListViewState, MoviesListSideEffect>(MoviesListViewState()) {

    init {
        initialize()
    }

    private fun initialize() = intent {
        reduce {
            state.copy(
                items = listOf()
            )
        }
    }
}

internal data class MoviesListViewState(
    val items: List<Movie> = listOf()
)

internal sealed class MoviesListSideEffect {

}

enum class MoviesListArguments {

}