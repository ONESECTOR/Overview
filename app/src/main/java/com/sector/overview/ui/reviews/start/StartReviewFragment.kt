package com.sector.overview.ui.reviews.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.R
import com.sector.overview.databinding.FragmentStartReviewBinding
import com.sector.overview.ui.dialogs.SuccessDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.viewmodel.observe

class StartReviewFragment : Fragment(R.layout.fragment_start_review) {

    private val viewBinding: FragmentStartReviewBinding by viewBinding(FragmentStartReviewBinding::bind)

    private val args: StartReviewFragmentArgs by navArgs()

    private val viewModel by viewModel<StartReviewViewModel> {
        parametersOf(
            args.movie
        )
    }

    private val successDialog: SuccessDialog by lazy {
        SuccessDialog(
            onCloseDialog = {
                findNavController().popBackStack()
            }
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
        viewBinding.btnSend.setOnClickListener {
            viewModel.sendReview(
                shortDescription = viewBinding.etShortDescription.text.toString(),
                review = viewBinding.etReview.text.toString()
            )
        }

        viewBinding.sliderPlot.addOnChangeListener { _, value, _ ->
            viewModel.onChangePlotValue(value)
        }
        viewBinding.sliderActingPerformance.addOnChangeListener { _, value, _ ->
            viewModel.onChangeActingPerformanceValue(value)
        }
        viewBinding.sliderDirection.addOnChangeListener { _, value, _ ->
            viewModel.onChangeDirectionValue(value)
        }
        viewBinding.sliderArtisticDesign.addOnChangeListener { _, value, _ ->
            viewModel.onChangeArtisticDesignValue(value)
        }
        viewBinding.sliderEditing.addOnChangeListener { _, value, _ ->
            viewModel.onChangeEditingValue(value)
        }
        viewBinding.sliderMusicAndSoundDesign.addOnChangeListener { _, value, _ ->
            viewModel.onChangeMusicAndSoundDesignValue(value)
        }
        viewBinding.sliderOriginality.addOnChangeListener { _, value, _ ->
            viewModel.onChangeOriginalityValue(value)
        }
        viewBinding.sliderEmotionalImpact.addOnChangeListener { _, value, _ ->
            viewModel.onChangeEmotionalImpactValue(value)
        }
    }

    private fun handleState(state: StartReviewViewState) {
        viewBinding.toolbar.title = state.movie?.name

        viewBinding.tvPlotValue.text = getString(R.string.review_value_max_20, state.plotValue.toInt())
        viewBinding.tvActingPerformanceValue.text = getString(R.string.review_value_max_15, state.actingPerformanceValue.toInt())
        viewBinding.tvDirectionValue.text = getString(R.string.review_value_max_15, state.directionValue.toInt())
        viewBinding.tvArtisticDesignValue.text = getString(R.string.review_value_max_15, state.artisticDesignValue.toInt())
        viewBinding.tvEditingValue.text = getString(R.string.review_value_max_10, state.editingValue.toInt())
        viewBinding.tvMusicAndSoundDesignValue.text = getString(R.string.review_value_max_10, state.musicAndSoundDesignValue.toInt())
        viewBinding.tvOriginalityValue.text = getString(R.string.review_value_max_10, state.originalityValue.toInt())
        viewBinding.tvEmotionalImpactValue.text = getString(R.string.review_value_max_5, state.emotionalImpactValue.toInt())
    }

    private fun handleSideEffect(sideEffect: StartReviewSideEffect) {
        when(sideEffect) {
            is StartReviewSideEffect.SuccessDialog -> {
                successDialog.show(childFragmentManager, SuccessDialog.SUCCESS_DIALOG_TAG)
            }
            else -> {

            }
        }
    }
}