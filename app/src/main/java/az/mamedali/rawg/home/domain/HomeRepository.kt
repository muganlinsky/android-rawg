package az.mamedali.rawg.home.domain

import az.mamedali.rawg.core.domain.NetworkError
import az.mamedali.rawg.core.domain.Result
import az.mamedali.rawg.home.data.GameResponse

interface HomeRepository {
    suspend fun fetchTrendingGames(): Result<GameResponse, NetworkError>
    suspend fun fetchAllGames(): Result<GameResponse, NetworkError>
}