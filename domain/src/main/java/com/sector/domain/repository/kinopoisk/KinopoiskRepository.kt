package com.sector.domain.repository.kinopoisk

import com.sector.domain.entity.kinopoisk.Movie
import kotlinx.coroutines.flow.Flow

interface KinopoiskRepository {

    suspend fun getMovies(): Flow<List<Movie>>
}