package az.mamedali.rawg.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import az.mamedali.rawg.R
import az.mamedali.rawg.core.ui.components.TextHeadlineSmall
import az.mamedali.rawg.core.ui.components.TextTitleMedium
import az.mamedali.rawg.home.ui.components.GameCard
import az.mamedali.rawg.home.ui.components.GameCardError
import az.mamedali.rawg.home.ui.components.GameCardLoading
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    onGameClick: (Int) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val trendingGamesUiState by viewModel.trendingGamesUiState.collectAsState()
    val allGamesUiState by viewModel.allGamesUiState.collectAsState()
    HomeUi(
        trendingGamesUiState = trendingGamesUiState,
        allGamesUiState = allGamesUiState,
        onGameClick = onGameClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUi(
    modifier: Modifier = Modifier,
    trendingGamesUiState: GamesUiState,
    allGamesUiState: GamesUiState,
    onGameClick: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CenterAlignedTopAppBar(
            title = {
                TextHeadlineSmall(
                    text = stringResource(R.string.home_title)
                )
            }
        )
        GameSection(
            text = stringResource(R.string.home_top_trending_title)
        )
        GameLazyRow(
            gamesUiState = trendingGamesUiState,
            onGameClick = onGameClick
        )
        GameSection(
            text = stringResource(R.string.home_all_games_title)
        )
        GameLazyRow(
            gamesUiState = allGamesUiState,
            onGameClick = onGameClick
        )
    }
}

@Composable
fun GameSection(
    modifier: Modifier = Modifier,
    text: String
) {
    Row(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {

            }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextTitleMedium(
            modifier = Modifier.weight(1f),
            text = text
        )
        Icon(
            modifier = Modifier.padding(start = 8.dp),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Composable
fun GameLazyRow(
    modifier: Modifier = Modifier,
    gamesUiState: GamesUiState,
    onGameClick: (Int) -> Unit
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (gamesUiState) {
            is GamesUiState.Success -> {
                items(gamesUiState.games) {
                    GameCard(
                        game = it,
                        onClick = onGameClick
                    )
                }
            }

            is GamesUiState.Error -> {
                item {
                    GameCardError()
                }
            }

            is GamesUiState.Loading -> {
                items(5) {
                    GameCardLoading()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeUiPreview() {
    HomeUi(
        trendingGamesUiState = GamesUiState.Loading,
        allGamesUiState = GamesUiState.Error,
        onGameClick = {}
    )
}