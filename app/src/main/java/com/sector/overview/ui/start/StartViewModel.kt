package com.sector.overview.ui.start

import com.sector.ui.viewmodel.BaseViewModel
import org.orbitmvi.orbit.syntax.simple.intent

internal class StartViewModel(

): BaseViewModel<StartViewState, StartSideEffect>(StartViewState()) {

    init {
        initialize()
    }

    private fun initialize() = intent {

    }
}

internal data class StartViewState(
    val items: List<String> = emptyList()
)

internal sealed class StartSideEffect {

}