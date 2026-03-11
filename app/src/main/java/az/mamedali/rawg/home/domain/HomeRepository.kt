package az.mamedali.rawg.home.domain

import az.mamedali.rawg.core.domain.network.NetworkError
import az.mamedali.rawg.core.domain.network.Result
import az.mamedali.rawg.core.data.GameResponse

interface HomeRepository {
    suspend fun fetchTrendingGames(): Result<GameResponse, NetworkError>
    suspend fun fetchAllGames(): Result<GameResponse, NetworkError>
}