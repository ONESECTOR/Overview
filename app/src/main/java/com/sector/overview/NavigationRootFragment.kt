package com.sector.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ContentView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.NavigationRes
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment

/**
 * Class that includes the logic for working with changing navigation in the inner container of fragments
 *
 * Implementation includes saving the state of the root fragments
 *
 * Root fragments are initialized in a lazy way
 *
 * The class that inherits from this class must implement the [fragmentContainerId] property
 */
abstract class NavigationRootFragment : Fragment {

    constructor() : super()

    @ContentView
    constructor(@LayoutRes layoutId: Int) : super(layoutId)

    abstract val fragmentContainerId: Int
    private var selectedFragment: NavHostFragment? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.getInt(SAVED_GRAPH_ID)?.let { graphId ->
            savedInstanceState.getString(SAVED_FRAGMENT_TAG)?.let { fragmentTag ->
                selectedFragment = obtainNavHostFragment(
                    fragmentManager = childFragmentManager,
                    fragmentTag = fragmentTag,
                    navGraphId = graphId,
                    containerId = fragmentContainerId,
                    isRecreate = false,
                    arguments = savedInstanceState.getBundle(SAVED_FRAGMENT_ARGUMENTS)
                )
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        selectedFragment?.navController?.let { graph ->
            outState.putInt(SAVED_GRAPH_ID, graph.graph.id)
        }

        selectedFragment?.let { fragment ->
            outState.putString(SAVED_FRAGMENT_TAG, fragment.tag)
            outState.putBundle(SAVED_FRAGMENT_ARGUMENTS, fragment.arguments)
        }
    }

    private fun obtainNavHostFragment(
        fragmentManager: FragmentManager,
        fragmentTag: String,
        @NavigationRes navGraphId: Int,
        @IdRes containerId: Int,
        isRecreate: Boolean,
        arguments: Bundle?
    ): NavHostFragment {
        val existingFragment =
            fragmentManager.findFragmentByTag(fragmentTag) as NavHostFragment?
        if (!isRecreate) {
            existingFragment?.let { return it }
        }
        val navHostFragment = NavHostFragment.create(navGraphId, arguments)
        fragmentManager.beginTransaction()
            .apply {
                if (isRecreate && existingFragment != null) {
                    remove(existingFragment)
                }
            }
            .add(containerId, navHostFragment, fragmentTag)
            .commitNow()
        return navHostFragment
    }

    protected fun switchToGraph(
        @NavigationRes graphId: Int,
        index: Int,
        isRecreate: Boolean = false,
        arguments: Bundle? = null
    ) {
        val newFragment = obtainNavHostFragment(
            childFragmentManager,
            "navigation#$index",
            graphId,
            fragmentContainerId,
            isRecreate,
            arguments
        )
        val transaction = childFragmentManager.beginTransaction()
        with(transaction) {
            selectedFragment?.let { fragment ->
                detach(fragment)
            }
            attach(newFragment)
            commitNow()
        }
        selectedFragment = newFragment
    }

    private companion object {
        private const val SAVED_GRAPH_ID = "saved_graph_id"
        private const val SAVED_FRAGMENT_TAG = "saved_fragment_tag"
        private const val SAVED_FRAGMENT_ARGUMENTS = "saved_fragment_arguments"
    }
}