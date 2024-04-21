package com.sector.overview.ui.auth

import com.sector.ui.viewmodel.BaseViewModel

internal class AuthViewModel: BaseViewModel<AuthViewState, AuthSideEffect>(AuthViewState()) {

    init {

    }
}

internal data class AuthViewState(
    val items: List<String> = emptyList()
)

internal sealed class AuthSideEffect {

}