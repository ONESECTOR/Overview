package com.sector.overview.ui.about

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.BuildConfig
import com.sector.overview.R
import com.sector.overview.databinding.FragmentAboutBinding

class AboutFragment : Fragment(R.layout.fragment_about) {

    private val viewBinding: FragmentAboutBinding by viewBinding(FragmentAboutBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnVk.setOnClickListener { onOpenBrowser(getString(R.string.vk_url)) }
        viewBinding.btnTelegram.setOnClickListener { onOpenBrowser(getString(R.string.telegram_url)) }
        viewBinding.tvAppVersion.text = getString(R.string.about_version, BuildConfig.VERSION_NAME)
        viewBinding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun onOpenBrowser(url: String) {
        val customTabsStyle = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(ContextCompat.getColor(requireActivity(), R.color.purple_background))
            .build()
        val customTabsIntent =
            CustomTabsIntent.Builder()
                .setShareState(CustomTabsIntent.SHARE_STATE_OFF)
                .setDefaultColorSchemeParams(customTabsStyle)

        customTabsIntent.build().launchUrl(requireActivity(), Uri.parse(url))
    }
}