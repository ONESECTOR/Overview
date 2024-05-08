package com.sector.overview.ui.home

import android.content.Context
import android.util.Log
import androidx.annotation.StringRes
import com.google.firebase.firestore.FirebaseFirestore
import com.sector.domain.entity.firebase.Review
import com.sector.domain.entity.kinopoisk.Category
import com.sector.domain.usecase.kinopoisk.KinopoiskUseCase
import com.sector.domain.entity.kinopoisk.HomeItem
import com.sector.overview.R
import com.sector.overview.di.services.UserService
import com.sector.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.catch
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import java.util.Calendar

internal class HomeViewModel(
    private val kinopoiskUseCase: KinopoiskUseCase,
    private val firestoreDatabase: FirebaseFirestore,
    private val userService: UserService
) : BaseViewModel<FeedViewState, FeedSideEffect>(FeedViewState()) {

    private val context: Context by inject()

    init {
        setGreeting()
        getMovies()
    }

    private fun setGreeting() = intent {
        userService.authState.collect { authState ->
            reduce {
                state.copy(
                    greetingsTitle = when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
                        in 6..11 -> R.string.greetings_morning
                        in 12..17 -> R.string.greetings_day
                        in 18..22 -> R.string.greetings_evening
                        else -> R.string.greetings_night
                    },
                    nickname = authState.nickname
                )
            }
        }
    }

    private fun getMovies() = intent {
        kinopoiskUseCase.getMovies()
            .catch {
                Log.d("TAG!", it.message.toString())
                reduce {
                    state.copy(
                        movies = null
                    )
                }
            }
            .collect { movies ->
                reduce {
                    state.copy(
                        greetingsTitle = when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
                            in 6..11 -> R.string.greetings_morning
                            in 12..17 -> R.string.greetings_day
                            in 18..22 -> R.string.greetings_evening
                            else -> R.string.greetings_night
                        },
                        movies = movies,
                        reviews = listOf(
                            Review(
                                authorNickname = "Кто-то",
                                shortDescription = "И вроде бы фильм понравился, но...",
                                reviewText = "Как и ожидалось, у братьев Руссо снова получился фильм, в котором опять наличествуют сценарные дыры, в котором опять есть большие проблемы с логикой, и в котором сюжет опять...",
                                sumRating = 64,
                                movieName = "Аватар"
                            ),
                            Review(
                                authorNickname = "Всеволод Сокур",
                                shortDescription = "И вроде бы фильм понравился, но...",
                                reviewText = "Как и ожидалось, у братьев Руссо снова получился фильм, в котором опять наличествуют сценарные дыры, в котором опять есть большие проблемы с логикой, и в котором сюжет опять...",
                                sumRating = 75,
                                movieName = "Мстители: Финал"
                            )
                        )
                    )
                }
            }
    }
}

internal data class FeedViewState(
    val movies: List<HomeItem>? = null,
    val reviews: List<Review>? = null,
    val categories: List<Category> = listOf(),
    val nickname: String? = null,
    @StringRes val greetingsTitle: Int = 0
)

internal sealed class FeedSideEffect {

}