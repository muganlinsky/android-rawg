package az.mamedali.rawg.game_detail.data

import az.mamedali.rawg.room.dao.GameDao
import az.mamedali.rawg.room.entities.GameEntity

class GameLocalDataSource(
    private val dao: GameDao
) {
    suspend fun fetchGame(gameId: Int): GameEntity? {
        return dao.getGameById(gameId)
    }

    suspend fun saveGame(game: GameEntity) {
        dao.insertGame(game)
    }
}