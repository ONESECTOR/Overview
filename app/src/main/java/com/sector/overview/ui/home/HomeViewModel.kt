package com.sector.overview.ui.home

import android.content.Context
import android.util.Log
import androidx.annotation.StringRes
import com.google.firebase.firestore.FirebaseFirestore
import com.sector.domain.entity.kinopoisk.Category
import com.sector.domain.usecase.kinopoisk.KinopoiskUseCase
import com.sector.domain.entity.kinopoisk.HomeItem
import com.sector.overview.R
import com.sector.overview.ui.home.entity.CategoryItem
import com.sector.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import java.util.Calendar

internal class HomeViewModel(
    private val kinopoiskUseCase: KinopoiskUseCase,
    private val firestoreDatabase: FirebaseFirestore
): BaseViewModel<FeedViewState, FeedSideEffect>(FeedViewState()) {

    private val context: Context by inject()

    init {
        getMovies()
    }

    private fun getMovies() = intent {
        kinopoiskUseCase.getMovies().onStart {
            reduce {
                state.copy(
                    loadingState = FeedViewState.LoadingState,
                    errorState = null,
                    items = null
                )
            }
        }.catch {
            Log.d("TAG!", it.message.toString())
            reduce {
                state.copy(
                    loadingState = null,
                    errorState = FeedViewState.ErrorState,
                    items = null
                )
            }
        }.collect { movies ->
            reduce {
                state.copy(
                    loadingState = null,
                    errorState = null,
                    greetingsTitle = when(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
                        in 6..11 -> R.string.greetings_morning
                        in 12..17 -> R.string.greetings_day
                        in 18..22 -> R.string.greetings_evening
                        else -> R.string.greetings_night
                    },
                    items = buildList {
                        add(
                            CategoryItem(
                                categoryName = context.getString(R.string.home_category_best),
                                hasAction = true,
                                actionName = context.getString(R.string.home_category_action_all)
                            )
                        )
                        addAll(movies)
                        add(
                            CategoryItem(
                                categoryName = context.getString(R.string.home_category_popular_reviews)
                            )
                        )
                    }
                )
            }
        }
    }
}

internal data class FeedViewState(
    val items: List<HomeItem>? = null,
    val loadingState: LoadingState? = null,
    val errorState: ErrorState? = null,
    val categories: List<Category> = listOf(),
    @StringRes val greetingsTitle: Int = 0
) {
    object LoadingState
    object ErrorState
}

internal sealed class FeedSideEffect {

}