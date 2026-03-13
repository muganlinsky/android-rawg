package az.mamedali.rawg.search.data

import az.mamedali.rawg.core.data.safeCall
import az.mamedali.rawg.core.domain.network.NetworkError
import az.mamedali.rawg.core.domain.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class GenresRemoteDataSource(
    private val client: HttpClient
) {
    suspend fun fetchGenres(): Result<GenreResponse, NetworkError> {
        return safeCall {
            client.get("api/genres")
        }
    }
}