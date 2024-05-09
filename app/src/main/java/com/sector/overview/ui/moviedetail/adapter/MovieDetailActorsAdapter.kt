package com.sector.overview.ui.moviedetail.adapter

import androidx.recyclerview.widget.DiffUtil
import coil.load
import coil.transform.RoundedCornersTransformation
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sector.domain.entity.kinopoisk.Person
import com.sector.overview.databinding.ItemMovieDetailActorBinding

internal class MovieDetailActorsAdapter : AsyncListDifferDelegationAdapter<Person>(ActorsDiffUtilCallback()) {

    init {
        with(delegatesManager) {
            addDelegate(actorDelegateAdapter())
        }
    }

    private fun actorDelegateAdapter() =
        adapterDelegateViewBinding<Person, Person, ItemMovieDetailActorBinding>(
            viewBinding = { layoutInflater, parent ->
                ItemMovieDetailActorBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            }
        ) {
            bind {
                binding.apply {
                    ivPerson.load(item.photo) {
                        transformations(RoundedCornersTransformation(12f))
                    }
                }
            }
        }
}

private class ActorsDiffUtilCallback : DiffUtil.ItemCallback<Person>() {

    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem != newItem
}