package com.sector.overview.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.R
import com.sector.overview.databinding.FragmentLoginBinding
import com.sector.overview.utils.activityNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.orbitmvi.orbit.viewmodel.observe

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewBinding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    private val viewModel by viewModel<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            state = ::handleState,
            sideEffect = ::handleSideEffect
        )

        viewBinding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        viewBinding.btnLogin.setOnClickListener {
            viewModel.login(
                email = viewBinding.etEmail.text.toString(),
                password = viewBinding.etPassword.text.toString()
            )
        }
    }

    private fun handleState(state: LoginViewState) {

    }

    private fun handleSideEffect(sideEffect: LoginSideEffect) {
        when(sideEffect) {
            is LoginSideEffect.Toast -> {
                Toast.makeText(requireContext(), sideEffect.message, Toast.LENGTH_SHORT).show()
            }
            is LoginSideEffect.Success -> {
                activityNavController().navigate(
                    directions = LoginFragmentDirections.actionLoginFragmentToHostFragment()
                )
            }
        }
    }
}