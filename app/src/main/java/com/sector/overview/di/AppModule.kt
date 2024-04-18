package com.sector.overview.di

import android.content.Context
import org.koin.dsl.module
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile

val appModule = module {
    single { provideDataStore(get()) }
}

internal fun provideDataStore(context: Context): DataStore<Preferences> =
    PreferenceDataStoreFactory.create(
        produceFile = {
            context.preferencesDataStoreFile("overview_app")
        }
    )

