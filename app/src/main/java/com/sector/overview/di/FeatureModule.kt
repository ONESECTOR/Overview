package com.sector.overview.di

import com.sector.overview.ui.start.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureModule = module {
    viewModelOf(::StartViewModel)
}