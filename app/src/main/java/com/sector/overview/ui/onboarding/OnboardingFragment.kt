package com.sector.overview.ui.onboarding

import android.os.Bundle
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.text.buildSpannedString
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.R
import com.sector.overview.databinding.FragmentOnboardingBinding
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
        viewBinding.tvLogin.text = buildSpannedString {
            append(getString(R.string.onboarding_login))
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    onOpenLogin()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }
            val clickablePart = getString(R.string.onboarding_login_clickable_part)
            val clickableStart = indexOf(clickablePart)
            setSpan(clickableSpan, clickableStart, clickableStart + clickablePart.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        viewBinding.btnStart.setOnClickListener {
            onOpenLogin()
        }
    }

    private fun handleState(state: OnboardingViewState) {

    }

    private fun handleSideEffect(sideEffect: OnboardingSideEffect) {

    }

    private fun onOpenLogin() {

    }
}