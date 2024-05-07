package com.sector.overview.ui.host

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import com.sector.core.utils.ReadOnlyOnceProperty
import com.sector.overview.NavigationRootFragment
import com.sector.overview.R
import com.sector.overview.databinding.FragmentHostBinding

class HostFragment : NavigationRootFragment(), ExternalNavigation, ExtrasProvider, BottomNavSizeProvider {

    override val fragmentContainerId: Int
        get() = R.id.main_container

    private var _binding: FragmentHostBinding? = null
    private val binding get() = _binding!!

    private var navigationExtras: ReadOnlyOnceProperty<Bundle?>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHostBinding.inflate(inflater, container, false)

        binding.navView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.navigation_home -> {
                    switchToGraph(R.navigation.home_graph, 0)
                }
                R.id.navigation_reviews -> {
                    switchToGraph(R.navigation.reviews_graph, 1)
                }
                R.id.navigation_profile -> {
                    switchToGraph(R.navigation.profile_graph, 2)
                }
            }
            true
        }

        binding.navView.setOnItemReselectedListener {
            val fragment = childFragmentManager.findFragmentById(R.id.main_container)
            val visibleFragment = fragment?.childFragmentManager?.fragments?.firstOrNull()
            (visibleFragment as? ReselectedAware)?.onReselect()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragment = childFragmentManager.findFragmentById(R.id.main_container)
        val firstLaunch = fragment?.childFragmentManager?.fragments?.isEmpty() != false
        if (firstLaunch) {
            switchToGraph(R.navigation.home_graph, 0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun navigateTo(menuId: Int, extras: Bundle?) {
        this.navigationExtras = extras?.let {
            ReadOnlyOnceProperty(initialValue = it)
        }
        if (binding.navView.menu.findItem(menuId) != null) {
            binding.navView.selectedItemId = menuId
        }
    }

    override fun getExtras(): Bundle? {
        return navigationExtras?.get()
    }

    override fun getBottomNavSize(): Int {
        return binding.navView.height - binding.navView.paddingBottom
    }
}

fun interface BottomNavSizeProvider {
    fun getBottomNavSize(): Int
}

fun interface ExtrasProvider {
    fun getExtras(): Bundle?
}

interface ExternalNavigation {
    fun navigateTo(@IdRes menuId: Int, extras: Bundle? = null)
}

fun interface ReselectedAware {
    fun onReselect()
}