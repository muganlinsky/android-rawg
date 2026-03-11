package az.mamedali.rawg.home.domain

import az.mamedali.rawg.core.domain.Game
import az.mamedali.rawg.core.domain.network.NetworkError
import az.mamedali.rawg.core.domain.network.Result

class GetAllGamesUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Result<List<Game>, NetworkError> {
        return when (val result = repository.fetchAllGames()) {
            is Result.Success -> Result.Success(result.data.results.map { it.toGame() })
            is Result.Error -> Result.Error(result.error)
        }
    }
}