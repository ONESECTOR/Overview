package com.sector.overview.di

import android.content.Context
import androidx.startup.Initializer
import com.sector.data.di.dataModule
import com.sector.data.di.networkModule
import com.sector.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class KoinInitializer : Initializer<KoinApplication> {

    override fun create(context: Context): KoinApplication {
        return startKoin {
            androidContext(context)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    dataModule,
                    domainModule,
                    featureModule
                )
            )
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}