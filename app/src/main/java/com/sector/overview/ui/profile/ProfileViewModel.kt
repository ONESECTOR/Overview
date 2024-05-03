package com.sector.overview.ui.profile

import com.sector.ui.viewmodel.BaseViewModel

internal class ProfileViewModel(

): BaseViewModel<ProfileViewState, ProfileSideEffect>(ProfileViewState()) {

    init {

    }
}

internal data class ProfileViewState(
    val name: String = ""
)

internal sealed class ProfileSideEffect {

}