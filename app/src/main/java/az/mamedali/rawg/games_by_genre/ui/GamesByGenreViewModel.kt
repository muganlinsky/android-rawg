package az.mamedali.rawg.games_by_genre.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import az.mamedali.rawg.core.domain.Game
import az.mamedali.rawg.core.domain.network.onError
import az.mamedali.rawg.core.domain.network.onSuccess
import az.mamedali.rawg.core.ui.Route
import az.mamedali.rawg.games_by_genre.domain.GetGamesByGenreUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GamesByGenreViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getGamesByGenreUseCase: GetGamesByGenreUseCase
) : ViewModel() {
    private val _gamesByGenreUiState: MutableStateFlow<GamesByGenreUiState> = MutableStateFlow(GamesByGenreUiState.Loading)
    val gamesByGenreUiState: StateFlow<GamesByGenreUiState> = _gamesByGenreUiState.asStateFlow()

    init {
        fetchGamesByGenre()
    }

    private fun fetchGamesByGenre() = viewModelScope.launch {
        val route = savedStateHandle.toRoute<Route.GamesByGenre>()
        val genreId = route.genreId
        getGamesByGenreUseCase(genreId)
            .onSuccess { games ->
                _gamesByGenreUiState.update {
                    GamesByGenreUiState.Success(games)
                }
            }
            .onError {
                _gamesByGenreUiState.update {
                    GamesByGenreUiState.Error
                }
            }
    }

}

sealed class GamesByGenreUiState {
    data class Success(val games: List<Game>) : GamesByGenreUiState()
    object Loading : GamesByGenreUiState()
    object Error : GamesByGenreUiState()
}