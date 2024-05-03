package com.sector.domain.usecase.kinopoisk

import com.sector.domain.entity.kinopoisk.Movie
import kotlinx.coroutines.flow.Flow

interface KinopoiskUseCase {

    suspend fun getMovies(): Flow<List<Movie>>
}