package com.sector.overview.ui.reviews

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.domain.entity.firebase.Review
import com.sector.overview.NavGraphDirections
import com.sector.overview.R
import com.sector.overview.databinding.FragmentReviewsBinding
import com.sector.overview.ui.reviews.adapter.ReviewAdapter
import com.sector.overview.utils.activityNavController
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

        viewBinding.rvReviews.adapter = ReviewAdapter(
            onItemClick = { review ->
                onOpenReview(review)
            }
        )
    }

    private fun handleState(state: ReviewsViewState) {
        if (state.reviews.isEmpty()) {
            viewBinding.tvYourReviewsTitle.visibility = View.GONE
            viewBinding.rvReviews.visibility = View.GONE
            viewBinding.emptyPlaceholder.visibility = View.VISIBLE
        } else {
            viewBinding.tvYourReviewsTitle.visibility = View.VISIBLE
            viewBinding.rvReviews.visibility = View.VISIBLE
            viewBinding.emptyPlaceholder.visibility = View.GONE
            (viewBinding.rvReviews.adapter as ReviewAdapter).items = state.reviews
        }
    }

    private fun handleSideEffect(sideEffect: ReviewsSideEffect) {
        when (sideEffect) {
            is ReviewsSideEffect.Toast -> {
                Toast.makeText(requireContext(), sideEffect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onOpenReview(review: Review) {
        activityNavController().navigate(
            directions = NavGraphDirections.actionGlobalReviewDetailFragment(review)
        )
    }
}