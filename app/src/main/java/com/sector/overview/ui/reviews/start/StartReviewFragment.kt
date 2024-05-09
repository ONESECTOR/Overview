package com.sector.overview.ui.reviews.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.R
import com.sector.overview.databinding.FragmentStartReviewBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            state = ::handleState,
            sideEffect = ::handleSideEffect
        )
    }

    private fun handleState(state: StartReviewViewState) {

    }

    private fun handleSideEffect(sideEffect: StartReviewSideEffect) {

    }
}