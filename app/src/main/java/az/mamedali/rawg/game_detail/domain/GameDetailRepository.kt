package az.mamedali.rawg.game_detail.domain

interface GameDetailRepository {
    suspend fun getGameDetail(gameId: Int): GameDetail?
}