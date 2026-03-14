package az.mamedali.rawg.game_detail.domain

import az.mamedali.rawg.core.domain.network.NetworkError
import az.mamedali.rawg.core.domain.network.Result

class GetGameDetailUseCase(
    private val repository: GameDetailRepository
) {
    suspend operator fun invoke(gameId: Int): Result<GameDetail, NetworkError> {
        val result = repository.getGameDetail(gameId)
        return if (result != null) {
            Result.Success(result)
        } else {
            Result.Error(NetworkError.UNKNOWN)
        }
    }
}