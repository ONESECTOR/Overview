package com.sector.data.di

import com.sector.data.repository.kinopoisk.KinopoiskRepositoryImpl
import com.sector.domain.repository.kinopoisk.KinopoiskRepository
import org.koin.dsl.module

val dataModule = module {
    single<KinopoiskRepository> { KinopoiskRepositoryImpl(get()) }
}