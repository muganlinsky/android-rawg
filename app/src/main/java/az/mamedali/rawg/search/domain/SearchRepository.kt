package az.mamedali.rawg.search.domain

import az.mamedali.rawg.core.domain.network.NetworkError
import az.mamedali.rawg.core.domain.network.Result
import az.mamedali.rawg.search.data.GenreResponse

interface SearchRepository {
    suspend fun fetchGenres(): Result<GenreResponse, NetworkError>
}