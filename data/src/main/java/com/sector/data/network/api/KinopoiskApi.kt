package com.sector.data.network.api

import com.sector.data.network.ResponseDataWrapper
import com.sector.data.network.response.kinopoisk.MovieResponse
import retrofit2.http.GET

internal interface KinopoiskApi {

    @GET("movie?page=1&limit=15&selectFields=id&selectFields=name&selectFields=type&selectFields=year&selectFields=description&selectFields=shortDescription&selectFields=slogan&selectFields=rating&selectFields=movieLength&selectFields=ageRating&selectFields=backdrop&selectFields=poster&selectFields=genres&selectFields=countries&selectFields=persons&lists=guy_ritchie")
    suspend fun getMovies(): ResponseDataWrapper
}