package com.sector.overview.ui.home

import android.content.Context
import android.util.Log
import androidx.annotation.StringRes
import com.sector.domain.entity.kinopoisk.Category
import com.sector.domain.usecase.kinopoisk.KinopoiskUseCase
import com.sector.domain.entity.kinopoisk.FeedItem
import com.sector.overview.R
import com.sector.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import java.util.Calendar

internal class HomeViewModel(
    private val kinopoiskUseCase: KinopoiskUseCase
): BaseViewModel<FeedViewState, FeedSideEffect>(FeedViewState()) {

    private val context: Context by inject()

    private val categories = listOf(
        Category(name = context.getString(R.string.category_comedy)),
        Category(name = context.getString(R.string.category_drama)),
        Category(name = context.getString(R.string.category_fighter)),
        Category(name = context.getString(R.string.category_horror)),
        Category(name = context.getString(R.string.category_fantasy)),
        Category(name = context.getString(R.string.category_detectives))
    )

    init {
        getAllCategories()
        getMovies()
    }

    fun onTabSelected() {

    }

    private fun getAllCategories() = intent {
        reduce {
            state.copy(
                categories = categories
            )
        }
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
                    items = movies
                )
            }
        }
    }

    private fun getSeries() = intent {

    }
}

internal data class FeedViewState(
    val items: List<FeedItem>? = null,
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