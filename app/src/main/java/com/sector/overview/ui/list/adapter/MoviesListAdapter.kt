package com.sector.overview.ui.list.adapter

import androidx.recyclerview.widget.DiffUtil
import coil.load
import coil.transform.RoundedCornersTransformation
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sector.domain.entity.kinopoisk.Movie
import com.sector.overview.databinding.ItemListMovieBinding

internal class MoviesListAdapter(
    private val onItemClick: () -> Unit
): AsyncListDifferDelegationAdapter<Movie>(MovieListDiffUtilCallback()) {

    init {
        with(delegatesManager) {
            addDelegate(movieDelegateAdapter(onItemClick))
        }
    }

    private fun movieDelegateAdapter(
        onItemClick: () -> Unit
    ) = adapterDelegateViewBinding<Movie, Movie, ItemListMovieBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemListMovieBinding.inflate(
                layoutInflater,
                parent,
                false
            )
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

private class MovieListDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem != newItem
}
