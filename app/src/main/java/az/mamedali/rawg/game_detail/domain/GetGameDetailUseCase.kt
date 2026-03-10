package az.mamedali.rawg.game_detail.domain

import az.mamedali.rawg.core.domain.NetworkError
import az.mamedali.rawg.core.domain.Result

class GetGameDetailUseCase(
    private val repository: GameDetailRepository
) {
    suspend operator fun invoke(gameId: Int): Result<GameDetail, NetworkError> {
        return when (val result = repository.getGameDetail(gameId)) {
            is Result.Success -> Result.Success(result.data.toGameDetail())
            is Result.Error -> Result.Error(result.error)
        }
    }
}