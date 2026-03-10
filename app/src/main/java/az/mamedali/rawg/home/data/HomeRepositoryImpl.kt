package az.mamedali.rawg.home.data

import az.mamedali.rawg.core.domain.NetworkError
import az.mamedali.rawg.core.domain.Result
import az.mamedali.rawg.core.data.safeCall
import az.mamedali.rawg.home.domain.HomeRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class HomeRepositoryImpl(
    private val client: HttpClient
) : HomeRepository {
    override suspend fun fetchTrendingGames(): Result<GameResponse, NetworkError> {
        return safeCall {
            client.get("api/games") {
                parameter("page", "1")
                parameter("page_size", "10")
                parameter("ordering", "-added")
                parameter("dates", "2025-12-01,2026-12-31")
            }
        }
    }

    override suspend fun fetchAllGames(): Result<GameResponse, NetworkError> {
        return safeCall {
            client.get("api/games") {
                parameter("page", "1")
                parameter("page_size", "10")
                parameter("ordering", "-added")
            }
        }
    }
}