package com.sector.overview.ui.home.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import coil.load
import coil.transform.RoundedCornersTransformation
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sector.domain.entity.kinopoisk.HomeItem
import com.sector.domain.entity.kinopoisk.Movie
import com.sector.overview.databinding.ItemHomeCategoryBinding
import com.sector.overview.databinding.ItemHomeMovieBinding
import com.sector.overview.ui.home.entity.CategoryItem

internal class HomeAdapter(
    private val onItemClick: () -> Unit,
    private val onActionClick: () -> Unit
): AsyncListDifferDelegationAdapter<HomeItem>(FeedDiffUtilCallback()) {

    init {
        with(delegatesManager) {
            addDelegate(movieDelegate(onItemClick))
            addDelegate(titleCategoryDelegateAdapter(onActionClick))
        }
    }

    fun setItems(items: List<HomeItem>?, onItemsAdded: () -> Unit) {
        differ.submitList(items) {
            onItemsAdded.invoke()
        }
    }

    private fun movieDelegate(
        onItemClick: () -> Unit
    ) = adapterDelegateViewBinding<Movie, HomeItem, ItemHomeMovieBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemHomeMovieBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        },
        on = { item, _, _ ->
            item is Movie
        }
    ) {
        bind {
            binding.apply {
                ivMoviePoster.load(item.poster.previewUrl) {
                    transformations(RoundedCornersTransformation(32f))
                }

                tvMovieName.text = item.name
                tvMovieGenres.text = item.genres
                    .take(n = 2)
                    .joinToString(separator = ", ")
                    {
                        it.name.replaceFirstChar(Char::titlecase)
                    }

                root.setOnClickListener {
                    onItemClick.invoke()
                }
            }
        }
    }

    private fun titleCategoryDelegateAdapter(
        onActionClick: () -> Unit
    ) = adapterDelegateViewBinding<CategoryItem, HomeItem, ItemHomeCategoryBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemHomeCategoryBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        },
        on = { item, _, _ ->
            item is CategoryItem
        }
    ) {
        bind {
            binding.apply {
                tvCategoryName.text = item.categoryName

                tvCategoryAction.text = item.categoryName
                tvCategoryAction.isVisible = item.hasAction
                tvCategoryAction.setOnClickListener {
                    onActionClick.invoke()
                }
            }
        }
    }
}

private class FeedDiffUtilCallback : DiffUtil.ItemCallback<HomeItem>() {

    override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean =
        oldItem != newItem
}