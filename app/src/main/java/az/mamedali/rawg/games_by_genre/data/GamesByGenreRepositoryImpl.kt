package az.mamedali.rawg.games_by_genre.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import az.mamedali.rawg.core.domain.Game
import az.mamedali.rawg.games_by_genre.domain.GamesByGenreRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

class GamesByGenreRepositoryImpl(
    private val client: HttpClient
) : GamesByGenreRepository {
    override fun fetchGamesByGenrePaged(genreId: Int): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10,
                prefetchDistance = 1
            ),
            pagingSourceFactory = {
                GamesByGenrePagingSource(
                    client = client,
                    genreId = genreId
                )
            }
        ).flow
    }
}