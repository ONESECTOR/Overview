package com.sector.overview.ui.auth.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sector.domain.entity.firebase.User
import com.sector.ui.viewmodel.BaseViewModel
import org.orbitmvi.orbit.syntax.simple.intent

internal class LoginViewModel(
    private val firestoreDatabase: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
): BaseViewModel<LoginViewState, LoginSideEffect>(LoginViewState()) {

    init {

    }

    fun login(email: String, password: String) = intent {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }
}

internal data class LoginViewState(
    val name: String = ""
)

internal sealed class LoginSideEffect {

}