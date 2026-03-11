package az.mamedali.rawg.games_by_genre.domain

import az.mamedali.rawg.core.domain.Game
import az.mamedali.rawg.core.domain.network.Result
import az.mamedali.rawg.core.domain.network.NetworkError

class GetGamesByGenreUseCase(
    private val repository: GamesByGenreRepository
) {
    suspend operator fun invoke(genreId: Int): Result<List<Game>, NetworkError> {
        return when (val result = repository.fetchGamesByGenre(genreId)) {
            is Result.Success -> Result.Success(result.data.results.map { it.toGame() })
            is Result.Error -> Result.Error(result.error)
        }
    }
}