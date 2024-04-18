package com.sector.data.di

import com.sector.data.network.provider.provideChucker
import com.sector.data.network.provider.provideJson
import com.sector.data.network.provider.provideKinopoiskApi
import com.sector.data.network.provider.provideOkHttpClient
import com.sector.data.network.provider.provideRetrofit
import org.koin.dsl.module

val networkModule = module {
    single { provideChucker(get()) }
    single { provideJson() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get(), get()) }
    single { provideKinopoiskApi(get()) }
}