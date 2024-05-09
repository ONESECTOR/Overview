package com.sector.overview.ui.moviedetail.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sector.domain.entity.firebase.Review
import com.sector.overview.R
import com.sector.overview.databinding.ItemMovieDetailReviewBinding

internal class MovieDetailReviewsAdapter(
    private val onItemClick: (Review) -> Unit
) : AsyncListDifferDelegationAdapter<Review>(FeedDiffUtilCallback()) {

    init {
        with(delegatesManager) {
            addDelegate(reviewAdapterDelegate(onItemClick))
        }
    }

    private fun reviewAdapterDelegate(
        onItemClick: (Review) -> Unit
    ) = adapterDelegateViewBinding<Review, Review, ItemMovieDetailReviewBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemMovieDetailReviewBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        }
    ) {
        bind {
            binding.apply {
                tvShortDescription.text = item.shortDescription
                tvReview.text = item.reviewText
                item.sumRating?.let { rating ->
                    tvRatingPercent.text = getString(R.string.movie_detail_rating_percent, rating)
                }

                root.setOnClickListener {
                    onItemClick.invoke(item)
                }
            }
        }
    }
}

private class FeedDiffUtilCallback : DiffUtil.ItemCallback<Review>() {

    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
        oldItem != newItem
}