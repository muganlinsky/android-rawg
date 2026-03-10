package az.mamedali.rawg.home.domain

import az.mamedali.rawg.core.domain.NetworkError
import az.mamedali.rawg.core.domain.Result

class GetTrendingGamesUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Result<List<Game>, NetworkError> {
        return when (val result = repository.fetchTrendingGames()) {
            is Result.Success -> Result.Success(result.data.results.map { it.toGame() })
            is Result.Error -> Result.Error(result.error)
        }
    }
}