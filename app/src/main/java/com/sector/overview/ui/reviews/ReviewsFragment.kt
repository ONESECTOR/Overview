package com.sector.overview.ui.reviews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.R
import com.sector.overview.databinding.FragmentReviewsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.orbitmvi.orbit.viewmodel.observe

class ReviewsFragment : Fragment(R.layout.fragment_reviews) {

    private val viewBinding: FragmentReviewsBinding by viewBinding(FragmentReviewsBinding::bind)

    private val viewModel by viewModel<ReviewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            state = ::handleState,
            sideEffect = ::handleSideEffect
        )
    }

    private fun handleState(state: ReviewsViewState) {

    }

    private fun handleSideEffect(sideEffect: ReviewsSideEffect) {

    }
}