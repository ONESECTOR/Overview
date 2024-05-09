package com.sector.overview.ui.moviedetail

import android.content.Context
import com.sector.domain.entity.firebase.Review
import com.sector.domain.entity.kinopoisk.Movie
import com.sector.domain.entity.kinopoisk.Person
import com.sector.overview.R
import com.sector.overview.utils.formatMovieLength
import com.sector.ui.viewmodel.BaseViewModel
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class MovieDetailViewModel(
    private val movie: Movie
): BaseViewModel<MovieDetailViewState, MovieDetailSideEffect>(MovieDetailViewState()) {

    private val context: Context by inject()

    init {
        initialize()
    }

    private fun initialize() = intent {
        val genres = movie.genres
            .take(n = 2)
            .joinToString(separator = ", ")
            {
                it.name.replaceFirstChar(Char::titlecase)
            }
        val year = movie.year
        val movieLength = movie.movieLength.formatMovieLength()
        reduce {
            state.copy(
                movie = movie,
                movieInfo = context.getString(
                    R.string.movie_detail_movie_info,
                    genres, year,
                    movieLength
                ),
                actors = movie.persons.filter { it.enProfession == "actor" }
            )
        }
    }
}

internal data class MovieDetailViewState(
    val movie: Movie? = null,
    val movieInfo: String? = null,
    val actors: List<Person> = listOf(),
    val reviews: List<Review> = listOf()
)

internal sealed class MovieDetailSideEffect {

}