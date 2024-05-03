package com.sector.overview.di

import android.content.Context
import org.koin.dsl.module
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.firebase.firestore.FirebaseFirestore

val appModule = module {
    single { provideDataStore(get()) }
    single { provideFirebaseFirestore() }
}

internal fun provideDataStore(context: Context): DataStore<Preferences> =
    PreferenceDataStoreFactory.create(
        produceFile = {
            context.preferencesDataStoreFile("overview_app")
        }
    )

internal fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

