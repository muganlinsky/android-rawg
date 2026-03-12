package az.mamedali.rawg.games_by_genre.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.PagingData
import androidx.paging.cachedIn
import az.mamedali.rawg.core.domain.Game
import az.mamedali.rawg.core.ui.Route
import az.mamedali.rawg.games_by_genre.domain.GamesByGenreRepository
import kotlinx.coroutines.flow.Flow

class GamesByGenreViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: GamesByGenreRepository
) : ViewModel() {
    private val genreId: Int
        get() = savedStateHandle.toRoute<Route.GamesByGenre>().genreId
    val genreName: String
        get() = savedStateHandle.toRoute<Route.GamesByGenre>().genreName

    val gamesByGenre: Flow<PagingData<Game>> = repository
        .fetchGamesByGenrePaged(genreId)
        .cachedIn(viewModelScope)
}