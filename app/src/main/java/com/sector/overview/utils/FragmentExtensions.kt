package com.sector.overview.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sector.overview.R

fun Fragment.activityNavController(): NavController {
    return Navigation.findNavController(requireActivity(), R.id.fragment_container)
}