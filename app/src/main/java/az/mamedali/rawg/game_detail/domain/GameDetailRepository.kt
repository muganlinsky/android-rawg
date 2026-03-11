package az.mamedali.rawg.game_detail.domain

import az.mamedali.rawg.core.domain.network.NetworkError
import az.mamedali.rawg.core.domain.network.Result
import az.mamedali.rawg.game_detail.data.GameDetailDto

interface GameDetailRepository {
    suspend fun getGameDetail(gameId: Int): Result<GameDetailDto, NetworkError>
}