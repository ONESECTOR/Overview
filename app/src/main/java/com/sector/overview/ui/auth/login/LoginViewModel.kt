package com.sector.overview.ui.auth.login

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sector.domain.entity.firebase.User
import com.sector.overview.di.services.UserService
import com.sector.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

internal class LoginViewModel(
    private val firebaseAuth: FirebaseAuth,
    private val firestoreDatabase: FirebaseFirestore,
    private val userService: UserService
): BaseViewModel<LoginViewState, LoginSideEffect>(LoginViewState()) {

    fun login(email: String, password: String) = intent {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                it.user?.let { user ->
                    firestoreDatabase.collection("users")
                        .document(user.uid)
                        .get()
                        .addOnSuccessListener { document ->
                            val currentUser = document.toObject(User::class.java)
                            userService.login(
                                email = currentUser?.email,
                                password = currentUser?.password,
                                nickname = currentUser?.nickname
                            )
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