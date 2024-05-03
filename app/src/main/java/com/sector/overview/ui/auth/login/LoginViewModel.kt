package com.sector.overview.ui.auth.login

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.sector.domain.entity.firebase.User
import com.sector.ui.viewmodel.BaseViewModel
import org.orbitmvi.orbit.syntax.simple.intent

internal class LoginViewModel(
    private val firestoreDatabase: FirebaseFirestore
): BaseViewModel<LoginViewState, LoginSideEffect>(LoginViewState()) {

    init {

    }

    fun login() = intent {
        firestoreDatabase.collection("users")
            .document("user1")
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val user = document.toObject(User::class.java)

                    user?.let {
                        Log.d("TAG!", it.name)
                        Log.d("TAG!", it.name)
                    }
                }
            }
            .addOnFailureListener {
                Log.d("TAG!", it.message.toString())
            }
    }
}

internal data class LoginViewState(
    val name: String = ""
)

internal sealed class LoginSideEffect {

}