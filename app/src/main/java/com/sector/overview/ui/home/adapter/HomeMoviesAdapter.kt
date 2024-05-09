package com.sector.overview.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import coil.load
import coil.transform.RoundedCornersTransformation
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sector.domain.entity.kinopoisk.HomeItem
import com.sector.domain.entity.kinopoisk.Movie
import com.sector.overview.databinding.ItemHomeMovieBinding

internal class HomeMoviesAdapter(
    private val onItemClick: (Movie) -> Unit
): AsyncListDifferDelegationAdapter<HomeItem>(FeedDiffUtilCallback()) {

    init {
        with(delegatesManager) {
            addDelegate(movieDelegate(onItemClick))
        }
    }

    fun setItems(items: List<HomeItem>?, onItemsAdded: () -> Unit) {
        differ.submitList(items) {
            onItemsAdded.invoke()
        }
    }

    private fun movieDelegate(
        onItemClick: (Movie) -> Unit
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
                    onItemClick.invoke(item)
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