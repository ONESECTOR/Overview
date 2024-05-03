package com.sector.data.repository.kinopoisk

import com.sector.data.network.api.KinopoiskApi
import com.sector.data.network.response.kinopoisk.toDomain
import com.sector.domain.entity.kinopoisk.Movie
import com.sector.domain.repository.kinopoisk.KinopoiskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class KinopoiskRepositoryImpl(
    private val kinopoiskApi: KinopoiskApi
): KinopoiskRepository {

    override suspend fun getMovies(): Flow<List<Movie>> = flow {
        emit(
            kinopoiskApi.getMovies().docs.toDomain()
        )
    }.flowOn(Dispatchers.IO)
}