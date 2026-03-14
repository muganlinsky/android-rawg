package az.mamedali.rawg.game_detail.data

import az.mamedali.rawg.core.domain.network.Result
import az.mamedali.rawg.game_detail.domain.GameDetail
import az.mamedali.rawg.game_detail.domain.GameDetailRepository

class GameDetailRepositoryImpl(
    private val remoteDataSource: GameRemoteDataSource,
    private val localDataSource: GameLocalDataSource
): GameDetailRepository {
    override suspend fun getGameDetail(gameId: Int): GameDetail? {
        val localGame = localDataSource.fetchGame(gameId)
        if (localGame != null) {
            return localGame.toGameDetail()
        }
        val response = remoteDataSource.fetchGameDetail(gameId)
        if (response is Result.Success) {
            localDataSource.saveGame(response.data.toGameDetailEntity())
            return response.data.toGameDetail()
        }
        return null
    }
}