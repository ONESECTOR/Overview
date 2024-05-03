package com.sector.domain.usecase.kinopoisk

import com.sector.domain.entity.kinopoisk.Movie
import com.sector.domain.repository.kinopoisk.KinopoiskRepository
import kotlinx.coroutines.flow.Flow

class KinopoiskUseCaseImpl(
    private val kinopoiskRepository: KinopoiskRepository
): KinopoiskUseCase {

    override suspend fun getMovies(): Flow<List<Movie>> =
        kinopoiskRepository.getMovies()
}