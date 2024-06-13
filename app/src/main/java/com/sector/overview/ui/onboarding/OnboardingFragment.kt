package com.sector.overview.ui.onboarding

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.R
import com.sector.overview.databinding.FragmentOnboardingBinding
import com.sector.overview.utils.spannableBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.orbitmvi.orbit.viewmodel.observe

class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {

    private val viewBinding: FragmentOnboardingBinding by viewBinding(FragmentOnboardingBinding::bind)

    private val viewModel by viewModel<OnboardingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            state = ::handleState,
            sideEffect = ::handleSideEffect
        )

        viewBinding.tvLogin.movementMethod = LinkMovementMethod()
        viewBinding.tvLogin.text = requireContext().spannableBuilder {
            append(getString(R.string.onboarding_login))
            appendSpace(getString(R.string.onboarding_login_clickable_part)) {
                color(R.color.purple_main)
                click {
                    findNavController().navigate(
                        directions = OnboardingFragmentDirections.actionOnboardingFragmentToLoginFragment()
                    )
                }
            }
        }

        viewBinding.btnStart.setOnClickListener {
            findNavController().navigate(
                directions = OnboardingFragmentDirections.actionOnboardingFragmentToRegisterFragment()
            )
        }
        viewBinding.btnSkip.setOnClickListener {
            findNavController().navigate(
                directions = OnboardingFragmentDirections.actionOnboardingFragmentToHostFragment()
            )
        }
    }

    private fun handleState(state: OnboardingViewState) {

    }

    private fun handleSideEffect(sideEffect: OnboardingSideEffect) {

    }
}