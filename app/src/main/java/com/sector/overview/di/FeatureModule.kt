package com.sector.overview.di

import com.sector.overview.ui.start.StartViewModel
import com.sector.overview.ui.home.HomeViewModel
import com.sector.overview.ui.list.MoviesListViewModel
import com.sector.overview.ui.moviedetail.MovieDetailViewModel
import com.sector.overview.ui.profile.ProfileViewModel
import com.sector.overview.ui.reviews.ReviewsViewModel
import com.sector.overview.ui.onboarding.OnboardingViewModel
import com.sector.overview.ui.auth.login.LoginViewModel
import com.sector.overview.ui.auth.register.RegisterViewModel
import com.sector.overview.ui.reviews.detail.ReviewDetailViewModel
import com.sector.overview.ui.reviews.start.StartReviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureModule = module {
    viewModelOf(::StartViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::MoviesListViewModel)
    viewModelOf(::ProfileViewModel)
    viewModel { params ->
        MovieDetailViewModel(
            movie = params[0]
        )
    }
    viewModelOf(::ReviewsViewModel)
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModel { params ->
        ReviewDetailViewModel(
            review = params[0]
        )
    }
    viewModel { params ->
        StartReviewViewModel(
            movie = params[0]
        )
    }
}