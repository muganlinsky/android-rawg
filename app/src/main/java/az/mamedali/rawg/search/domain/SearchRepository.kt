package az.mamedali.rawg.search.domain

import az.mamedali.rawg.core.domain.NetworkError
import az.mamedali.rawg.core.domain.Result
import az.mamedali.rawg.search.data.GenreResponse

interface SearchRepository {
    suspend fun fetchGenres(): Result<GenreResponse, NetworkError>
}