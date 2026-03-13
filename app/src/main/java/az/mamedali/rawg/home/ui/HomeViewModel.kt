package az.mamedali.rawg.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mamedali.rawg.core.domain.network.onError
import az.mamedali.rawg.core.domain.network.onSuccess
import az.mamedali.rawg.core.domain.Game
import az.mamedali.rawg.home.domain.GetAllGamesUseCase
import az.mamedali.rawg.home.domain.GetTrendingGamesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTrendingGamesUseCase: GetTrendingGamesUseCase,
    private val getAllGamesUseCase: GetAllGamesUseCase
) : ViewModel() {
    private val _trendingGamesUiState: MutableStateFlow<GamesUiState> = MutableStateFlow(GamesUiState.Loading)
    val trendingGamesUiState: StateFlow<GamesUiState> = _trendingGamesUiState.asStateFlow()

    private val _allGamesUiState: MutableStateFlow<GamesUiState> = MutableStateFlow(GamesUiState.Loading)
    val allGamesUiState: StateFlow<GamesUiState> = _allGamesUiState.asStateFlow()
    
    init {
        fetchTrendingGames()
        fetchAllGames()
    }

    private fun fetchTrendingGames() = viewModelScope.launch {
        getTrendingGamesUseCase()
            .onSuccess { games ->
                _trendingGamesUiState.update {
                    GamesUiState.Success(games)
                }
            }
            .onError {
                _trendingGamesUiState.update {
                    GamesUiState.Error
                }
            }
    }

    private fun fetchAllGames() = viewModelScope.launch {
        getAllGamesUseCase()
            .onSuccess { games ->
                _allGamesUiState.update {
                    GamesUiState.Success(games)
                }
            }
            .onError {
                _allGamesUiState.update {
                    GamesUiState.Error
                }
            }
    }
}

sealed class GamesUiState {
    data class Success(val games: List<Game>) : GamesUiState()
    object Loading : GamesUiState()
    object Error : GamesUiState()

    fun isSuccess(): Success? = this as? Success
    val isLoading: Boolean
        get() = this is Loading
    val isError: Boolean
        get() = this is Error
}