package com.sector.overview.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sector.domain.entity.firebase.Review
import com.sector.overview.R
import com.sector.overview.databinding.ItemHomeReviewBinding

class HomeReviewsAdapter(
    private val onItemClick: () -> Unit
) : AsyncListDifferDelegationAdapter<Review>(HomeReviewDiffUtilCallback()) {

    init {
        with(delegatesManager) {
            addDelegate(reviewDelegateAdapter(onItemClick))
        }
    }

    private fun reviewDelegateAdapter(
        onItemClick: () -> Unit
    ) = adapterDelegateViewBinding<Review, Review, ItemHomeReviewBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemHomeReviewBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        }
    ) {
        bind {
            binding.apply {
                tvAuthorNickname.text = item.authorNickname
                tvReviewShortDescription.text = item.shortDescription
                tvReviewText.text = item.reviewText
                item.sumRating?.let { rating ->
                    tvMovieRating.text = getString(R.string.home_rating_percent, rating)
                }
                tvMovieName.text = item.movieName

                root.setOnClickListener {
                    onItemClick.invoke()
                }
            }
        }
    }
}

private class HomeReviewDiffUtilCallback : DiffUtil.ItemCallback<Review>() {

    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
        oldItem != newItem
}