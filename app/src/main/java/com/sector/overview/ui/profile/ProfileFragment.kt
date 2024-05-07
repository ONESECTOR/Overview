package com.sector.overview.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.NavGraphDirections
import com.sector.overview.R
import com.sector.overview.databinding.FragmentProfileBinding
import com.sector.overview.utils.activityNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.orbitmvi.orbit.viewmodel.observe

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewBinding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)

    private val viewModel by viewModel<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            state = ::handleState,
            sideEffect = ::handleSideEffect
        )

        viewBinding.btnAbout.setOnClickListener { onAboutClick() }
        viewBinding.btnLogOut.setOnClickListener { viewModel.signOut() }
    }

    private fun handleState(state: ProfileViewState) {

    }

    private fun handleSideEffect(sideEffect: ProfileSideEffect) {
        when(sideEffect) {
            is ProfileSideEffect.SignOut -> {
                activityNavController().navigate(
                    directions = NavGraphDirections.actionGlobalOnboardingFragment()
                )
            }
        }
    }

    private fun onAboutClick() {
        activityNavController().navigate(
            directions = NavGraphDirections.actionGlobalAboutFragment()
        )
    }
}