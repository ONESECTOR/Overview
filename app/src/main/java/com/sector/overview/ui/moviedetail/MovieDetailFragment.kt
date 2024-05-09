package com.sector.overview.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.RoundedCornersTransformation
import com.sector.domain.entity.firebase.Review
import com.sector.domain.entity.kinopoisk.Movie
import com.sector.overview.NavGraphDirections
import com.sector.overview.R
import com.sector.overview.databinding.FragmentMovieDetailBinding
import com.sector.overview.ui.moviedetail.adapter.MovieDetailActorsAdapter
import com.sector.overview.ui.moviedetail.adapter.MovieDetailReviewsAdapter
import com.sector.overview.utils.activityNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.viewmodel.observe

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val viewBinding: FragmentMovieDetailBinding by viewBinding(FragmentMovieDetailBinding::bind)

    private val viewModel by viewModel<MovieDetailViewModel> {
        parametersOf(
            args.movie
        )
    }

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            state = ::handleState,
            sideEffect = ::handleSideEffect
        )

        viewBinding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        viewBinding.rvActors.adapter = MovieDetailActorsAdapter()
        viewBinding.rvReviews.adapter = MovieDetailReviewsAdapter(
            onItemClick = { review ->
                onOpenReview(review)
            }
        )
    }

    private fun handleState(state: MovieDetailViewState) {
        state.movie?.let { movie ->
            viewBinding.tvMovieName.text = movie.name
            viewBinding.tvDescription.text = movie.description
            viewBinding.ivMoviePoster.load(movie.poster.previewUrl) {
                transformations(RoundedCornersTransformation(32f))
            }

            viewBinding.btnStartReview.setOnClickListener { onOpenStartReview(movie) }
        }

        viewBinding.tvMovieInfo.text = state.movieInfo

        (viewBinding.rvActors.adapter as MovieDetailActorsAdapter).items = state.actors
        (viewBinding.rvReviews.adapter as MovieDetailReviewsAdapter).items = state.reviews
    }

    private fun handleSideEffect(sideEffect: MovieDetailSideEffect) {
        when (sideEffect) {
            is MovieDetailSideEffect.Toast -> {
                Toast.makeText(requireContext(), sideEffect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onOpenReview(review: Review) {
        activityNavController().navigate(
            directions = NavGraphDirections.actionGlobalReviewDetailFragment(review)
        )
    }

    private fun onOpenStartReview(movie: Movie) {
        activityNavController().navigate(
            directions = NavGraphDirections.actionGlobalStartReviewFragment(movie)
        )
    }
}