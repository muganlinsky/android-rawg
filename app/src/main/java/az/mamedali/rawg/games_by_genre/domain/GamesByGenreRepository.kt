package az.mamedali.rawg.games_by_genre.domain

import az.mamedali.rawg.core.data.GameResponse
import az.mamedali.rawg.core.domain.network.NetworkError
import az.mamedali.rawg.core.domain.network.Result

interface GamesByGenreRepository {
    suspend fun fetchGamesByGenre(genreId: Int): Result<GameResponse, NetworkError>
}