package az.mamedali.rawg.game_detail.data

import az.mamedali.rawg.core.data.safeCall
import az.mamedali.rawg.core.domain.network.NetworkError
import az.mamedali.rawg.core.domain.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class GameRemoteDataSource(
    private val client: HttpClient
) {
    suspend fun fetchGameDetail(gameId: Int): Result<GameDetailDto, NetworkError> {
        return safeCall {
            client.get("api/games/$gameId")
        }
    }
}