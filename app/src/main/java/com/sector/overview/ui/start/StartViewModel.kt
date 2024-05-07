package com.sector.overview.ui.start

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.viewModelScope
import com.sector.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.replay
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

internal class StartViewModel(
    private val dataStore: DataStore<Preferences>,
): BaseViewModel<StartViewState, StartSideEffect>(StartViewState()) {

    init {
        initialize()
    }

    private fun initialize() = intent {
        dataStore.data.map {
            it[booleanPreferencesKey(IS_NEED_AUTH)]
        }.collect { isNeedAuth ->
            when (isNeedAuth) {
                true -> {
                    postSideEffect(StartSideEffect.Authorization(isNeedAuth = true))
                }
                false -> {
                    postSideEffect(StartSideEffect.Authorization(isNeedAuth = false))
                }
                null -> {
                    postSideEffect(StartSideEffect.Authorization(isNeedAuth = true))
                }
            }
        }
        /*combine(
            dataStore.data.map {
                it[booleanPreferencesKey(IS_NEED_AUTH)]
            }
        ) { isNeedAuth ->
            when {
                isNeedAuth.firstOrNull() == true -> {
                    postSideEffect(StartSideEffect.Authorization(isNeedAuth = true))
                }
                isNeedAuth.firstOrNull() == false -> {
                    postSideEffect(StartSideEffect.Authorization(isNeedAuth = false))
                }
            }
        }.launchIn(viewModelScope)*/
    }

    companion object {
        const val IS_NEED_AUTH = "is_need_auth"
    }
}

internal data class StartViewState(
    val items: List<String> = emptyList()
)

internal sealed class StartSideEffect {
    data class Authorization(val isNeedAuth: Boolean): StartSideEffect()
}