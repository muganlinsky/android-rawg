package az.mamedali.rawg.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.mamedali.rawg.core.domain.network.onSuccess
import az.mamedali.rawg.search.domain.Genre
import az.mamedali.rawg.search.domain.GetGenresUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getGenresUseCase: GetGenresUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getGenresUseCase()
                .onSuccess { response ->
                    _uiState.update {
                        it.copy(
                            genres = response.results.map { it.toGenre() }
                        )
                    }
                }
        }
    }
}

data class SearchUiState(
    val genres: List<Genre> = emptyList()
)