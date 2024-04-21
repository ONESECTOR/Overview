package com.sector.data.di

import com.sector.data.repository.KinopoiskRepositoryImpl
import com.sector.domain.repository.KinopoiskRepository
import org.koin.dsl.module

val dataModule = module {
    single<KinopoiskRepository> { KinopoiskRepositoryImpl(get()) }
}