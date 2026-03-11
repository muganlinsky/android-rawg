package az.mamedali.rawg.game_detail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import az.mamedali.rawg.core.domain.network.onError
import az.mamedali.rawg.core.domain.network.onSuccess
import az.mamedali.rawg.core.ui.Route
import az.mamedali.rawg.game_detail.domain.GameDetail
import az.mamedali.rawg.game_detail.domain.GetGameDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getGameDetailUseCase: GetGameDetailUseCase
): ViewModel() {
    private val _gameDetailUiState: MutableStateFlow<GameDetailUiState> = MutableStateFlow(GameDetailUiState.Loading)
    val gameDetailUiState: StateFlow<GameDetailUiState> = _gameDetailUiState.asStateFlow()

    init {
        fetchGameDetail()
    }

    private fun fetchGameDetail() = viewModelScope.launch {
        val route = savedStateHandle.toRoute<Route.GameDetail>()
        val gameId = route.gameId
        getGameDetailUseCase(gameId)
            .onSuccess { gameDetail ->
                _gameDetailUiState.update {
                    GameDetailUiState.Success(gameDetail)
                }
            }
            .onError {
                _gameDetailUiState.update {
                    GameDetailUiState.Error
                }
            }
    }
}

sealed class GameDetailUiState {
    data class Success(val gameDetail: GameDetail) : GameDetailUiState()
    object Loading : GameDetailUiState()
    object Error : GameDetailUiState()
}