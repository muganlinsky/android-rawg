package az.mamedali.rawg.games_by_genre.domain

import androidx.paging.PagingData
import az.mamedali.rawg.core.domain.Game
import kotlinx.coroutines.flow.Flow

interface GamesByGenreRepository {
    fun fetchGamesByGenrePaged(genreId: Int): Flow<PagingData<Game>>
}