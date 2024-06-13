package com.sector.overview.ui.auth.register

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sector.domain.entity.firebase.User
import com.sector.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import java.util.UUID

internal class RegisterViewModel(
    private val firebaseAuth: FirebaseAuth,
    private val firestoreDatabase: FirebaseFirestore
): BaseViewModel<RegisterViewState, RegisterSideEffect>(RegisterViewState()) {

    fun createAccount(email: String, password: String, nickname: String) = intent {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                it.user?.let { user ->
                    firestoreDatabase.collection("users")
                        .document(user.uid)
                        .set(
                            User(
                                id = UUID.randomUUID().toString(),
                                email = user.email,
                                password = password,
                                nickname = nickname
                            ).toMap()
                        )
                        .addOnSuccessListener {
                            viewModelScope.launch {
                                postSideEffect(RegisterSideEffect.Success)
                            }
                        }
                        .addOnFailureListener {
                            viewModelScope.launch {
                                postSideEffect(RegisterSideEffect.Toast(message = it.localizedMessage))
                            }
                        }
                }
            }
            .addOnFailureListener {
                viewModelScope.launch {
                    postSideEffect(RegisterSideEffect.Toast(message = it.localizedMessage))
                }
            }
    }
}

internal data class RegisterViewState(
    val name: String = ""
)

internal sealed class RegisterSideEffect {
    data object Success: RegisterSideEffect()
    data class Toast(val message: String?): RegisterSideEffect()
}