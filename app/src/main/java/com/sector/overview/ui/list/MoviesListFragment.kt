package com.sector.overview.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.R
import com.sector.overview.databinding.FragmentMoviesListBinding
import com.sector.overview.ui.list.adapter.MoviesListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.orbitmvi.orbit.viewmodel.observe

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private val viewBinding: FragmentMoviesListBinding by viewBinding(FragmentMoviesListBinding::bind)

    private val viewModel by viewModel<MoviesListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            state = ::handleState,
            sideEffect = ::handleSideEffect
        )

        viewBinding.rvMovies.adapter = MoviesListAdapter(
            onItemClick = {

            }
        )
    }

    private fun handleState(state: MoviesListViewState) {
        (viewBinding.rvMovies.adapter as MoviesListAdapter).items = state.items
    }

    private fun handleSideEffect(sideEffect: MoviesListSideEffect) {

    }
}