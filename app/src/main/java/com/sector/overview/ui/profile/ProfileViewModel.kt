package com.sector.overview.ui.profile

import com.google.firebase.auth.FirebaseAuth
import com.sector.overview.di.services.UserService
import com.sector.ui.viewmodel.BaseViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

internal class ProfileViewModel(
    private val firebaseAuth: FirebaseAuth,
    private val userService: UserService
): BaseViewModel<ProfileViewState, ProfileSideEffect>(ProfileViewState()) {

    init {
        initialize()
    }

    fun signOut() = intent {
        userService.signOut()
        firebaseAuth.signOut()
        postSideEffect(ProfileSideEffect.SignOut)
    }

    private fun initialize() = intent {
        userService.authState.collect { authState ->
            reduce {
                state.copy(nickname = authState.nickname)
            }
        }
    }
}

internal data class ProfileViewState(
    val nickname: String? = null
)

internal sealed class ProfileSideEffect {
    data object SignOut: ProfileSideEffect()
}