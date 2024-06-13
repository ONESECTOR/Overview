package com.sector.overview.ui.moviedetail

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.sector.domain.entity.firebase.Review
import com.sector.domain.entity.kinopoisk.Movie
import com.sector.domain.entity.kinopoisk.Person
import com.sector.overview.R
import com.sector.overview.di.services.AuthState
import com.sector.overview.di.services.UserService
import com.sector.overview.utils.formatMovieLength
import com.sector.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

internal class MovieDetailViewModel(
    private val movie: Movie,
    private val firestoreDatabase: FirebaseFirestore,
    private val userService: UserService
): BaseViewModel<MovieDetailViewState, MovieDetailSideEffect>(MovieDetailViewState()) {

    private val context: Context by inject()

    init {
        initialize()
        getAuthState()
    }

    private fun getAuthState() = intent {
        userService.authState.collect { authState ->
            reduce {
                state.copy(
                    authState = authState
                )
            }
        }
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
        firestoreDatabase.collection("reviews")
            .whereEqualTo("movieId", movie.id.toString())
            .get()
            .addOnSuccessListener { querySnapshot ->
                viewModelScope.launch {
                    reduce {
                        state.copy(
                            reviews = querySnapshot.documents.mapNotNull { document ->
                                document.toObject(Review::class.java)
                            }
                        )
                    }
                }
            }
            .addOnFailureListener {
                viewModelScope.launch {
                    postSideEffect(MovieDetailSideEffect.Toast(message = it.localizedMessage))
                }
            }
    }
}

internal data class MovieDetailViewState(
    val movie: Movie? = null,
    val movieInfo: String? = null,
    val actors: List<Person> = listOf(),
    val reviews: List<Review> = listOf(),
    val authState: AuthState? = null
)

internal sealed class MovieDetailSideEffect {
    data class Toast(val message: String?): MovieDetailSideEffect()
}