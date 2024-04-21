package com.sector.data.network.interceptor

import com.sector.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthHeaderInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("X-API-KEY", BuildConfig.KINOPOISK_API_KEY)
        return chain.proceed(request.build())
    }
}