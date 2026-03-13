package az.mamedali.rawg.search.domain

import az.mamedali.rawg.core.domain.network.NetworkError
import az.mamedali.rawg.core.domain.network.Result
import az.mamedali.rawg.search.data.GenreResponse

class GetGenresUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(): Result<GenreResponse, NetworkError> {
        return repository.fetchGenres()
    }
}