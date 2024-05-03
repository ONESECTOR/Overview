package com.sector.data.repository.firebase


import com.sector.domain.repository.firebase.FirebaseFirestoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.sector.domain.entity.firebase.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FirebaseFirestoreRepositoryImpl(
    private val firestoreDatabase: FirebaseFirestore
): FirebaseFirestoreRepository {

    /*override val user: Flow<User>
        get() = firestoreDatabase.collection("user1").whereEqualTo("", ""*/

    /*override suspend fun createUser(): Flow<User> = flow {
        firestoreDatabase.collection("users")
            .document("user1")
            .get()
            .addOnSuccessListener { document ->
                when {
                    document != null && document.exists() -> {
                        val user = document.toObject(User::class.java)
                        emit(user)
                    }
                    else -> {
                        emit(User("", ""))
                    }
                }
            }
            .addOnFailureListener {
                emit(User("", ""))
            }
    }.flowOn(Dispatchers.IO)*/
}