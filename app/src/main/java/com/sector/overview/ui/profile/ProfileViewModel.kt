package com.sector.overview.ui.profile

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sector.domain.entity.firebase.Review
import com.sector.overview.R
import com.sector.overview.di.services.AuthState
import com.sector.overview.di.services.LoginState
import com.sector.overview.di.services.UserService
import com.sector.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

internal class ProfileViewModel(
    private val firebaseAuth: FirebaseAuth,
    private val userService: UserService,
    private val firestoreDatabase: FirebaseFirestore
): BaseViewModel<ProfileViewState, ProfileSideEffect>(ProfileViewState()) {

    private val context: Context by inject()

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
            if (authState.loginState == LoginState.LoggedIn) {
                getReviewsCount(userId = authState.id)
            }
            reduce {
                state.copy(authState = authState)
            }
        }
    }

    private fun getReviewsCount(userId: String?)  = intent {
        firestoreDatabase.collection("reviews")
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                viewModelScope.launch {
                    reduce {
                        val reviews = querySnapshot.documents.mapNotNull { document ->
                            document.toObject(Review::class.java)
                        }.count()

                        state.copy(
                            status = when(reviews) {
                                in 1..10 -> context.getString(R.string.status_novice)
                                in 11..20 -> context.getString(R.string.status_enthusiast)
                                in 21..30 -> context.getString(R.string.status_cinephile)
                                in 31..50 -> context.getString(R.string.status_amateur_critic)
                                in 51..70 -> context.getString(R.string.status_experienced_critic)
                                in 71..90 -> context.getString(R.string.status_expert_critic)
                                in 91..99 -> context.getString(R.string.status_master_critic)
                                else -> context.getString(R.string.status_legendary_critic)
                            }
                        )
                    }
                }
            }
            .addOnFailureListener {
                viewModelScope.launch {
                    postSideEffect(ProfileSideEffect.Toast(message = it.localizedMessage))
                }
            }
    }
}

internal data class ProfileViewState(
    val authState: AuthState? = null,
    val status: String? = null,
)

internal sealed class ProfileSideEffect {
    data object SignOut: ProfileSideEffect()
    data class Toast(val message: String?) : ProfileSideEffect()
}