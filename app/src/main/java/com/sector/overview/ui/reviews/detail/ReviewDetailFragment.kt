package com.sector.overview.ui.reviews.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
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

        viewBinding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun handleState(state: ReviewDetailViewState) {
        state.review?.let { review ->
            viewBinding.toolbar.title = review.movieName

            viewBinding.tvShortDescription.text = review.shortDescription
            viewBinding.tvReview.text = review.reviewText

            viewBinding.tvPlotValue.text = getString(R.string.review_value_max_20, review.plotRating)
            viewBinding.tvActingPerformanceValue.text = getString(R.string.review_value_max_15, review.actingPerformance)
            viewBinding.tvDirectionValue.text = getString(R.string.review_value_max_15, review.direction)
            viewBinding.tvArtisticDesignValue.text = getString(R.string.review_value_max_15, review.artisticDesign)
            viewBinding.tvEditingValue.text = getString(R.string.review_value_max_10, review.editing)
            viewBinding.tvMusicAndSoundDesignValue.text = getString(R.string.review_value_max_10, review.musicAndSoundDesign)
            viewBinding.tvOriginalityValue.text = getString(R.string.review_value_max_10, review.originality)
            viewBinding.tvEmotionalImpactValue.text = getString(R.string.review_value_max_5, review.emotionalImpact)

            viewBinding.sliderPlot.value = review.plotRating?.toFloat() ?: 0f
            viewBinding.sliderActingPerformance.value = review.actingPerformance?.toFloat() ?: 0f
            viewBinding.sliderDirection.value = review.direction?.toFloat() ?: 0f
            viewBinding.sliderArtisticDesign.value = review.artisticDesign?.toFloat() ?: 0f
            viewBinding.sliderEditing.value = review.editing?.toFloat() ?: 0f
            viewBinding.sliderMusicAndSoundDesign.value = review.musicAndSoundDesign?.toFloat() ?: 0f
            viewBinding.sliderOriginality.value = review.originality?.toFloat() ?: 0f
            viewBinding.sliderEmotionalImpact.value = review.emotionalImpact?.toFloat() ?: 0f
        }
    }

    private fun handleSideEffect(sideEffect: ReviewDetailSideEffect) {

    }
}