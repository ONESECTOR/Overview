package com.sector.overview.ui.onboarding

import com.sector.ui.viewmodel.BaseViewModel

internal class OnboardingViewModel(

): BaseViewModel<OnboardingViewState, OnboardingSideEffect>(OnboardingViewState()) {

}

internal data class OnboardingViewState(
    val name: String = ""
)

internal sealed class OnboardingSideEffect {

}