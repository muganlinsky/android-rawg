package az.mamedali.rawg.games_by_genre.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import az.mamedali.rawg.core.domain.Game
import az.mamedali.rawg.core.ui.components.TextHeadlineSmall
import az.mamedali.rawg.games_by_genre.ui.components.GameRow
import org.koin.androidx.compose.koinViewModel

@Composable
fun GamesByGenreScreen(
    onGameClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    viewmodel: GamesByGenreViewModel = koinViewModel()
) {
    val uiState by viewmodel.gamesByGenreUiState.collectAsState()
    GamesByGenreUi(
        gamesByGenreUiState = uiState,
        onGameClick = onGameClick,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesByGenreUi(
    gamesByGenreUiState: GamesByGenreUiState,
    onGameClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {

            },
            navigationIcon = {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        )
        when (gamesByGenreUiState) {
            is GamesByGenreUiState.Success -> GamesByGenreUiSuccess(
                games = gamesByGenreUiState.games,
                onGameClick = onGameClick
            )

            is GamesByGenreUiState.Loading -> {

            }
            is GamesByGenreUiState.Error -> {

            }
        }
    }
}

@Composable
fun GamesByGenreUiSuccess(
    games: List<Game>,
    onGameClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        games.forEach {
            GameRow(
                game = it,
                onClick = onGameClick
            )
        }
    }
}

@Preview
@Composable
fun GamesByGenreUiPreview() {
    GamesByGenreUi(
        gamesByGenreUiState = GamesByGenreUiState.Loading,
        onGameClick = {},
        onBackClick = {}
    )
}