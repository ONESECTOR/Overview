package com.sector.data.network.api

import com.sector.data.network.ResponseDataWrapper
import com.sector.data.network.response.kinopoisk.MovieResponse
import retrofit2.http.GET

internal interface KinopoiskApi {

    @GET("movie?page=1&limit=15&selectFields=id&selectFields=name&selectFields=type&selectFields=year&selectFields=description&selectFields=shortDescription&selectFields=slogan&selectFields=rating&selectFields=movieLength&selectFields=ageRating&selectFields=backdrop&selectFields=poster&selectFields=genres&selectFields=countries&selectFields=persons&notNullFields=id&notNullFields=name&notNullFields=type&notNullFields=year&notNullFields=description&notNullFields=shortDescription&notNullFields=slogan&notNullFields=rating.kp&notNullFields=rating.imdb&notNullFields=movieLength&notNullFields=ageRating&notNullFields=backdrop.url&notNullFields=poster.url&notNullFields=genres.name&notNullFields=countries.name&notNullFields=persons.id&notNullFields=persons.photo&countries.name=!Россия")
    suspend fun getMovies(): ResponseDataWrapper
}