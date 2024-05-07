package com.sector.overview.ui.profile

import com.google.firebase.auth.FirebaseAuth
import com.sector.ui.viewmodel.BaseViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

internal class ProfileViewModel(
    private val firebaseAuth: FirebaseAuth
): BaseViewModel<ProfileViewState, ProfileSideEffect>(ProfileViewState()) {

    fun signOut() = intent {
        firebaseAuth.signOut()
        postSideEffect(ProfileSideEffect.SignOut)
    }
}

internal data class ProfileViewState(
    val name: String = ""
)

internal sealed class ProfileSideEffect {
    data object SignOut: ProfileSideEffect()
}