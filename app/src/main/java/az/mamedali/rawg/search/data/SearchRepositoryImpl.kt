package az.mamedali.rawg.search.data

import az.mamedali.rawg.core.domain.NetworkError
import az.mamedali.rawg.core.domain.Result
import az.mamedali.rawg.core.data.safeCall
import az.mamedali.rawg.search.domain.SearchRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class SearchRepositoryImpl(
    private val client: HttpClient
): SearchRepository {
    override suspend fun fetchGenres(): Result<GenreResponse, NetworkError> {
        return safeCall {
            client.get("api/genres")
        }
    }
}