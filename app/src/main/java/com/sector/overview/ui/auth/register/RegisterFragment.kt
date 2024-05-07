package com.sector.overview.ui.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.R
import com.sector.overview.databinding.FragmentRegisterBinding
import com.sector.overview.utils.activityNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.orbitmvi.orbit.viewmodel.observe

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val viewBinding: FragmentRegisterBinding by viewBinding(FragmentRegisterBinding::bind)

    private val viewModel by viewModel<RegisterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            state = ::handleState,
            sideEffect = ::handleSideEffect
        )

        viewBinding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        viewBinding.btnCreateAccount.setOnClickListener {
            viewModel.createAccount(
                email = viewBinding.etEmail.text.toString(),
                password = viewBinding.etPassword.text.toString(),
                nickname = viewBinding.etNickname.text.toString()
            )
        }
    }

    private fun handleState(state: RegisterViewState) {

    }

    private fun handleSideEffect(sideEffect: RegisterSideEffect) {
        when(sideEffect) {
            is RegisterSideEffect.Toast -> {
                Toast.makeText(requireContext(), sideEffect.message, Toast.LENGTH_SHORT).show()
            }
            is RegisterSideEffect.Success -> {
                activityNavController().navigate(
                    directions = RegisterFragmentDirections.actionRegisterFragmentToHostFragment()
                )
            }
            else -> {

            }
        }
    }
}