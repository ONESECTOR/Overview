package com.sector.overview.ui.reviews.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sector.domain.entity.firebase.Review
import com.sector.overview.R
import com.sector.overview.databinding.ItemReviewBinding

internal class ReviewAdapter(
    private val onItemClick: (Review) -> Unit
) : AsyncListDifferDelegationAdapter<Review>(ReviewDiffUtilCallback()) {

    init {
        with(delegatesManager) {
            addDelegate(reviewDelegateAdapter(onItemClick))
        }
    }

    private fun reviewDelegateAdapter(
        onItemClick: (Review) -> Unit
    ) = adapterDelegateViewBinding<Review, Review, ItemReviewBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemReviewBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        }
    ) {
        bind {
            binding.apply {
                tvMovieName.text = item.movieName
                item.sumRating?.let { rating ->
                    tvRatingPercent.text = getString(R.string.review_rating_percent, rating)
                }
                root.setOnClickListener {
                    onItemClick.invoke(item)
                }
            }
        }
    }
}

private class ReviewDiffUtilCallback : DiffUtil.ItemCallback<Review>() {

    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
        oldItem != newItem
}