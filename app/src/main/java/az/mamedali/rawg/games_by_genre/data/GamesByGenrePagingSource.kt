package az.mamedali.rawg.games_by_genre.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import az.mamedali.rawg.core.data.GameResponse
import az.mamedali.rawg.core.data.safeCall
import az.mamedali.rawg.core.domain.Game
import az.mamedali.rawg.core.domain.network.NetworkError
import az.mamedali.rawg.core.domain.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GamesByGenrePagingSource(
    private val client: HttpClient,
    private val genreId: Int
) : PagingSource<Int, Game>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize

            val response: Result<GameResponse, NetworkError> = safeCall {
                client.get("api/games") {
                    parameter("page", page)
                    parameter("page_size", pageSize)
                    parameter("genres", genreId)
                }
            }

            val successfulResponse: GameResponse? = runCatching {
                (response as Result.Success).data
            }
                .getOrNull()

            val games: List<Game> = when(response) {
                is Result.Success -> response.data.results.map { it.toGame() }
                is Result.Error -> emptyList()
            }

            LoadResult.Page(
                data = games,
                prevKey = if (page == 1) null else page - 1,
                nextKey = successfulResponse?.next?.let { page + 1 }
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }
}