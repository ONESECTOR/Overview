package com.sector.overview.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.sector.overview.R
import com.sector.overview.databinding.FragmentHomeBinding
import com.sector.overview.ui.home.adapter.HomeMoviesAdapter
import com.sector.overview.ui.home.adapter.HomeReviewsAdapter
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
            onItemClick = {

            }
        )
        viewBinding.vpReviews.adapter = HomeReviewsAdapter(
            onItemClick = {

            }
        )
        TabLayoutMediator(viewBinding.circleIndicator, viewBinding.vpReviews) { _, _ ->

        }.attach()
    }

    private fun handleState(state: FeedViewState) {
        (viewBinding.rvMovies.adapter as HomeMoviesAdapter).items = state.movies
        (viewBinding.vpReviews.adapter as HomeReviewsAdapter).items = state.reviews

        viewBinding.tvGreetings.text = requireContext().spannableBuilder {
            append(getString(state.greetingsTitle))
            appendLn(state.nickname.toString()) {
                color(R.color.white_f2)
            }
        }
    }

    private fun handleSideEffect(sideEffect: FeedSideEffect) {

    }
}