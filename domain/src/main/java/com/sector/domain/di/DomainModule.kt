package com.sector.domain.di

import com.sector.domain.repository.KinopoiskUseCase
import com.sector.domain.repository.KinopoiskUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::KinopoiskUseCaseImpl) { bind<KinopoiskUseCase>() }
}