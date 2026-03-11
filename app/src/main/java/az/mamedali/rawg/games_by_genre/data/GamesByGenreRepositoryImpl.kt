package az.mamedali.rawg.games_by_genre.data

import az.mamedali.rawg.core.data.GameResponse
import az.mamedali.rawg.core.data.safeCall
import az.mamedali.rawg.core.domain.network.NetworkError
import az.mamedali.rawg.core.domain.network.Result
import az.mamedali.rawg.games_by_genre.domain.GamesByGenreRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GamesByGenreRepositoryImpl(
    private val client: HttpClient
) : GamesByGenreRepository {
    override suspend fun fetchGamesByGenre(genreId: Int): Result<GameResponse, NetworkError> {
        return safeCall {
            client.get("api/games") {
                parameter("page", "1")
                parameter("page_size", "10")
                parameter("genres", genreId)
            }
        }
    }
}