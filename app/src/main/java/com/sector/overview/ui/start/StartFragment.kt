package com.sector.overview.ui.start

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.R
import com.sector.overview.databinding.FragmentStartBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment(R.layout.fragment_start) {

    private val viewModel by viewModel<StartViewModel>()

    private val viewBinding: FragmentStartBinding by viewBinding(FragmentStartBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val direction = StartFragmentDirections.actionStartFragmentToHostFragment()

        findNavController().navigate(direction)
    }
}