package com.sector.data.network.api

import com.sector.data.network.ResponseDataWrapper
import com.sector.data.network.response.kinopoisk.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface KinopoiskApi {

    @GET("https://api.kinopoisk.dev/v1.4/movie?page=1&limit=10")
    suspend fun getMovies(): ResponseDataWrapper

    @GET("https://api.kinopoisk.dev/v1.4/movie/search?page=1&limit=10")
    suspend fun search(@Query("query") query: String): ResponseDataWrapper
}