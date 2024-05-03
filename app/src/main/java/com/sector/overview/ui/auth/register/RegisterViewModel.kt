package com.sector.overview.ui.auth.register

import com.sector.ui.viewmodel.BaseViewModel

internal class RegisterViewModel(

): BaseViewModel<RegisterViewState, RegisterSideEffect>(RegisterViewState()) {

    init {

    }

    fun createAccount() {

    }
}

internal data class RegisterViewState(
    val name: String = ""
)

internal sealed class RegisterSideEffect {

}