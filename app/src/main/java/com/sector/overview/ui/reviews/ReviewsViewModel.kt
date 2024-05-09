package com.sector.overview.ui.reviews

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sector.domain.entity.firebase.Review
import com.sector.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.syntax.simple.repeatOnSubscription

internal class ReviewsViewModel(
    private val firestoreDatabase: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : BaseViewModel<ReviewsViewState, ReviewsSideEffect>(ReviewsViewState()) {

    init {
        initialize()
    }

    private fun initialize() = intent {
        repeatOnSubscription {
            firestoreDatabase.collection("reviews")
                .whereEqualTo("userId", firebaseAuth.currentUser?.uid.toString())
                .get()
                .addOnSuccessListener { querySnapshot ->
                    viewModelScope.launch {
                        reduce {
                            state.copy(
                                reviews = querySnapshot.documents.mapNotNull { document ->
                                    document.toObject(Review::class.java)
                                }
                            )
                        }
                    }
                }
                .addOnFailureListener {
                    viewModelScope.launch {
                        postSideEffect(ReviewsSideEffect.Toast(message = it.localizedMessage))
                    }
                }
        }
    }
}

internal data class ReviewsViewState(
    val reviews: List<Review> = listOf()
)

internal sealed class ReviewsSideEffect {
    data class Toast(val message: String?) : ReviewsSideEffect()
}