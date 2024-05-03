package com.sector.overview.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.R
import com.sector.overview.databinding.FragmentMovieDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.orbitmvi.orbit.viewmodel.observe

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val viewBinding: FragmentMovieDetailBinding by viewBinding(FragmentMovieDetailBinding::bind)

    private val viewModel by viewModel<MovieDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            state = ::handleState,
            sideEffect = ::handleSideEffect
        )
    }

    private fun handleState(state: MovieDetailViewState) {

    }

    private fun handleSideEffect(sideEffect: MovieDetailSideEffect) {

    }
}