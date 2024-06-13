package com.sector.overview.ui.start

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sector.domain.entity.firebase.User
import com.sector.overview.di.services.UserService
import com.sector.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

internal class StartViewModel(
    private val firebaseAuth: FirebaseAuth,
    private val firestoreDatabase: FirebaseFirestore,
    private val userService: UserService
): BaseViewModel<StartViewState, StartSideEffect>(StartViewState()) {

    init {
        initialize()
    }

    private fun initialize() = intent {
        when {
            firebaseAuth.currentUser != null -> {
                firebaseAuth.currentUser?.let { user ->
                    firestoreDatabase.collection("users")
                        .document(user.uid)
                        .get()
                        .addOnSuccessListener { document ->
                            val currentUser = document.toObject(User::class.java)
                            userService.login(
                                id = currentUser?.id,
                                email = currentUser?.email,
                                password = currentUser?.password,
                                nickname = currentUser?.nickname
                            )
                            viewModelScope.launch {
                                postSideEffect(StartSideEffect.Authorization(isNeedAuth = false))
                            }
                        }
                        .addOnFailureListener {
                            viewModelScope.launch {

                            }
                        }
                }
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