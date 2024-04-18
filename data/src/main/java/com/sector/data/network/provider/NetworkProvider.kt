package com.sector.data.network.provider

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sector.data.BuildConfig
import com.sector.data.network.api.KinopoiskApi
import com.sector.data.network.interceptor.AuthHeaderInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

internal fun provideChucker(context: Context): ChuckerInterceptor =
    ChuckerInterceptor.Builder(context)
        .collector(
            ChuckerCollector(
                context = context,
                showNotification = true,
                retentionPeriod = RetentionManager.Period.ONE_HOUR
            )
        )
        .maxContentLength(1024 * 1024 * 256)
        .alwaysReadResponseBody(true)
        .build()

internal fun provideJson() = Json {
    ignoreUnknownKeys = true
}

internal fun provideOkHttpClient(
    chucker: ChuckerInterceptor
): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(30L, TimeUnit.SECONDS)
        .writeTimeout(30L, TimeUnit.SECONDS)
        .addInterceptor(AuthHeaderInterceptor())
        .apply {
            if (listOf("debug").contains(BuildConfig.BUILD_TYPE)) {
                addNetworkInterceptor(
                    HttpLoggingInterceptor { message ->
                        Log.d("OkHttp", message)
                    }.setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                addNetworkInterceptor(chucker)
            }
        }
        .build()

internal fun provideRetrofit(
    okHttpClient: OkHttpClient,
    json: Json,
): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.KINOPOISK_ENDPOINT)
    .client(okHttpClient)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()

internal fun provideKinopoiskApi(retrofit: Retrofit): KinopoiskApi =
    retrofit.create(KinopoiskApi::class.java)
