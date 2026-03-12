package az.mamedali.rawg.games_by_genre.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import az.mamedali.rawg.core.domain.Game
import az.mamedali.rawg.core.ui.components.TextHeadlineSmall
import az.mamedali.rawg.games_by_genre.ui.components.GameRow
import org.koin.androidx.compose.koinViewModel

@Composable
fun GamesByGenreScreen(
    onGameClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    viewModel: GamesByGenreViewModel = koinViewModel()
) {
    val games = viewModel.gamesByGenre.collectAsLazyPagingItems()
    GamesByGenreUi(
        games = games,
        genreName = viewModel.genreName,
        onGameClick = onGameClick,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesByGenreUi(
    games: LazyPagingItems<Game>,
    genreName: String,
    onGameClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                TextHeadlineSmall(
                    text = genreName
                )
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
        GamesByGenreUi(
            games = games,
            onGameClick = onGameClick
        )
    }
}

@Composable
fun GamesByGenreUi(
    games: LazyPagingItems<Game>,
    onGameClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = games.itemCount,
            key = { index -> games[index]?.id ?: index }
        ) { index ->
            val game = games[index]
            if (game != null) {
                GameRow(
                    game = game,
                    onClick = onGameClick
                )
            }
        }
    }
}