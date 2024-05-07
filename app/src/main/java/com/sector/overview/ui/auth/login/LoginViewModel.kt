package com.sector.overview.ui.auth.login

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.sector.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

internal class LoginViewModel(
    private val firebaseAuth: FirebaseAuth
): BaseViewModel<LoginViewState, LoginSideEffect>(LoginViewState()) {

    fun login(email: String, password: String) = intent {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                viewModelScope.launch {
                    postSideEffect(LoginSideEffect.Success)
                }
            }
            .addOnFailureListener {
                viewModelScope.launch {
                    postSideEffect(LoginSideEffect.Toast(message = it.localizedMessage))
                }
            }
    }
}

internal data class LoginViewState(
    val name: String = ""
)

internal sealed class LoginSideEffect {
    data object Success: LoginSideEffect()
    data class Toast(val message: String?): LoginSideEffect()
}