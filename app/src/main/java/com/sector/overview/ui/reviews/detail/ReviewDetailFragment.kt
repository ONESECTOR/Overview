package com.sector.overview.ui.reviews.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.R
import com.sector.overview.databinding.FragmentReviewDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.viewmodel.observe

class ReviewDetailFragment : Fragment(R.layout.fragment_review_detail) {

    private val viewBinding: FragmentReviewDetailBinding by viewBinding(FragmentReviewDetailBinding::bind)

    private val args: ReviewDetailFragmentArgs by navArgs()

    private val viewModel by viewModel<ReviewDetailViewModel> {
        parametersOf(
            args.review
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            state = ::handleState,
            sideEffect = ::handleSideEffect
        )
    }

    private fun handleState(state: ReviewDetailViewState) {

    }

    private fun handleSideEffect(sideEffect: ReviewDetailSideEffect) {

    }
}