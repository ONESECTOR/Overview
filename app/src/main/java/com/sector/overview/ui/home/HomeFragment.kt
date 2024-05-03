package com.sector.overview.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.R
import com.sector.overview.databinding.FragmentHomeBinding
import com.sector.overview.databinding.ItemChipFilterBinding
import com.sector.overview.ui.home.adapter.HomeAdapter
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

        viewBinding.filters.setOnCheckedStateChangeListener { _, checkedIds ->
            //viewModel.onChangeFilter(checkedIds.first())
        }

        viewBinding.rvFeed.adapter = HomeAdapter(
            onItemClick = {

            }
        )
    }

    private fun handleState(state: FeedViewState) {
        when {
            state.loadingState != null -> {
                viewBinding.loadingView.isVisible = true
                viewBinding.errorView.isVisible = false
                viewBinding.rvFeed.isVisible = false
            }
            state.errorState != null -> {
                viewBinding.loadingView.isVisible = false
                viewBinding.errorView.isVisible = true
                viewBinding.rvFeed.isVisible = false
            }
            state.items != null -> {
                viewBinding.loadingView.isVisible = false
                viewBinding.errorView.isVisible = false
                viewBinding.rvFeed.isVisible = true

                state.categories.forEach {
                    val filter = ItemChipFilterBinding.inflate(
                        LayoutInflater.from(requireContext()),
                        viewBinding.filters,
                        false
                    ).apply {
                        //this.root.id = it.id
                        this.root.text = it.name
                        //this.root.isChecked = it.isActive
                    }
                    viewBinding.filters.addView(filter.root)
                }

                (viewBinding.rvFeed.adapter as HomeAdapter).setItems(state.items) {

                }
            }
        }
    }

    private fun handleSideEffect(sideEffect: FeedSideEffect) {

    }
}