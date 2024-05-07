package com.sector.overview.ui.start

import com.google.firebase.auth.FirebaseAuth
import com.sector.ui.viewmodel.BaseViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

internal class StartViewModel(
    private val firebaseAuth: FirebaseAuth
): BaseViewModel<StartViewState, StartSideEffect>(StartViewState()) {

    init {
        initialize()
    }

    private fun initialize() = intent {
        when {
            firebaseAuth.currentUser != null -> {
                postSideEffect(StartSideEffect.Authorization(isNeedAuth = false))
            }
            else -> {
                postSideEffect(StartSideEffect.Authorization(isNeedAuth = true))
            }
        }
    }
}

internal data class StartViewState(
    val items: List<String> = emptyList()
)

internal sealed class StartSideEffect {
    data class Authorization(val isNeedAuth: Boolean): StartSideEffect()
}