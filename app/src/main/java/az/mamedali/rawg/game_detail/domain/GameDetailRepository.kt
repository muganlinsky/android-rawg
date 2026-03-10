package az.mamedali.rawg.game_detail.domain

import az.mamedali.rawg.core.domain.NetworkError
import az.mamedali.rawg.core.domain.Result
import az.mamedali.rawg.game_detail.data.GameDetailDto

interface GameDetailRepository {
    suspend fun getGameDetail(gameId: Int): Result<GameDetailDto, NetworkError>
}