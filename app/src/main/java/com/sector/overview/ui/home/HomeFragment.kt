package com.sector.overview.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.sector.domain.entity.firebase.Review
import com.sector.domain.entity.kinopoisk.Movie
import com.sector.overview.NavGraphDirections
import com.sector.overview.R
import com.sector.overview.databinding.FragmentHomeBinding
import com.sector.overview.di.services.AuthState
import com.sector.overview.di.services.LoginState
import com.sector.overview.ui.home.adapter.HomeMoviesAdapter
import com.sector.overview.ui.home.adapter.HomeReviewsAdapter
import com.sector.overview.utils.activityNavController
import com.sector.overview.utils.spannableBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.orbitmvi.orbit.viewmodel.observe

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewBinding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel by viewModel<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            state = ::handleState,
            sideEffect = ::handleSideEffect
        )

        viewBinding.tvCategoryBestAction.setOnClickListener {

        }
        viewBinding.rvMovies.adapter = HomeMoviesAdapter(
            onItemClick = { movie ->
                onOpenMovieDetail(movie)
            }
        )
        viewBinding.vpReviews.adapter = HomeReviewsAdapter(
            onItemClick = { review ->
                onOpenReviewDetail(review)
            }
        )
        TabLayoutMediator(viewBinding.circleIndicator, viewBinding.vpReviews) { _, _ ->

        }.attach()
    }

    private fun handleState(state: FeedViewState) {
        (viewBinding.rvMovies.adapter as HomeMoviesAdapter).items = state.movies
        (viewBinding.vpReviews.adapter as HomeReviewsAdapter).items = state.reviews

        when(state.authState?.loginState) {
            LoginState.LoggedIn -> {
                viewBinding.tvGreetings.visibility = View.VISIBLE
                viewBinding.ivLogo.visibility = View.VISIBLE
            }
            else -> {
                viewBinding.tvGreetings.visibility = View.GONE
                viewBinding.ivLogo.visibility = View.GONE
            }
        }

        viewBinding.tvGreetings.text = requireContext().spannableBuilder {
            append(getString(state.greetingsTitle))
            appendLn(state.authState?.nickname.toString()) {
                color(R.color.white_f2)
            }
        }
    }

    private fun handleSideEffect(sideEffect: FeedSideEffect) {

    }

    private fun onOpenMovieDetail(movie: Movie) {
        activityNavController().navigate(
            directions = NavGraphDirections.actionGlobalMovieDetailFragment(movie)
        )
    }

    private fun onOpenReviewDetail(review: Review) {

    }
}