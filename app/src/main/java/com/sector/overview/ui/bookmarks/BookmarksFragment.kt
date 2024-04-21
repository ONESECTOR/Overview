package com.sector.overview.ui.bookmarks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.R
import com.sector.overview.databinding.FragmentBookmarksBinding

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private val viewBinding: FragmentBookmarksBinding by viewBinding(FragmentBookmarksBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}