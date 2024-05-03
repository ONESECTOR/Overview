package com.sector.overview.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import coil.load
import coil.transform.RoundedCornersTransformation
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sector.domain.entity.kinopoisk.FeedItem
import com.sector.domain.entity.kinopoisk.Movie
import com.sector.overview.databinding.ItemHomeMovieBinding

internal class HomeAdapter(
    private val onItemClick: () -> Unit
): AsyncListDifferDelegationAdapter<FeedItem>(FeedDiffUtilCallback()) {

    init {
        with(delegatesManager) {
            addDelegate(movieDelegate(onItemClick))
        }
    }

    fun setItems(items: List<FeedItem>?, onItemsAdded: () -> Unit) {
        differ.submitList(items) {
            onItemsAdded.invoke()
        }
    }

    private fun movieDelegate(
        onItemClick: () -> Unit
    ) = adapterDelegateViewBinding<Movie, FeedItem, ItemHomeMovieBinding>(
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
}

private class FeedDiffUtilCallback : DiffUtil.ItemCallback<FeedItem>() {

    override fun areItemsTheSame(oldItem: FeedItem, newItem: FeedItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: FeedItem, newItem: FeedItem): Boolean =
        oldItem != newItem
}