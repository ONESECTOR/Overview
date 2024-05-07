package com.sector.overview.ui.start

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.NavGraphDirections
import com.sector.overview.R
import com.sector.overview.databinding.FragmentStartBinding
import com.sector.overview.utils.activityNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.orbitmvi.orbit.viewmodel.observe

class StartFragment : Fragment(R.layout.fragment_start) {

    private val viewModel by viewModel<StartViewModel>()

    private val viewBinding: FragmentStartBinding by viewBinding(FragmentStartBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            state = ::handleState,
            sideEffect = ::handleSideEffect
        )
    }

    private fun handleState(state: StartViewState) {

    }

    private fun handleSideEffect(sideEffect: StartSideEffect) {
        when(sideEffect) {
            is StartSideEffect.Authorization -> {
                when {
                    sideEffect.isNeedAuth -> {
                        Log.d("TAG!", "true")
                        findNavController().navigate(
                            directions = StartFragmentDirections.actionStartFragmentToOnboardingFragment()
                        )
                    }
                    else -> {
                        Log.d("TAG!", "false")
                        activityNavController().navigate(
                            directions = StartFragmentDirections.actionStartFragmentToHostFragment()
                        )
                    }
                }
            }
        }
    }
}