package az.mamedali.rawg.game_detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import az.mamedali.rawg.R
import az.mamedali.rawg.core.ui.components.TextBodyMedium
import az.mamedali.rawg.core.ui.components.TextLabelMedium
import az.mamedali.rawg.core.ui.components.TextTitleLarge
import az.mamedali.rawg.core.ui.components.TextTitleMedium
import az.mamedali.rawg.game_detail.domain.GameDetail
import az.mamedali.rawg.game_detail.ui.components.ActionIconButton
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameDetailScreen(
    onBackClick: () -> Unit,
    viewModel: GameDetailViewModel = koinViewModel()
) {
    val gameDetailUiState by viewModel.gameDetailUiState.collectAsState()
    GameDetailUi(
        gameDetailUiState = gameDetailUiState,
        onBackClick = onBackClick
    )
}

@Composable
fun GameDetailUi(
    modifier: Modifier = Modifier,
    gameDetailUiState: GameDetailUiState,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (gameDetailUiState) {
            is GameDetailUiState.Success -> GameDetailUiSuccess(
                gameDetail = gameDetailUiState.gameDetail
            )

            is GameDetailUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is GameDetailUiState.Error -> {

            }
        }
        ActionIconButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp),
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            onClick = onBackClick
        )
        ActionIconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp),
            imageVector = Icons.Default.FavoriteBorder,
            onClick = {

            }
        )
    }
}

@Composable
fun GameDetailUiSuccess(
    gameDetail: GameDetail
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            model = gameDetail.image,
            contentDescription = gameDetail.name,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextTitleLarge(
                text = gameDetail.name
            )
            RatingRow(
                game = gameDetail
            )
            MetadataRow(
                game = gameDetail
            )
            if (gameDetail.platforms.isNotEmpty()) {
                PlatformSection(
                    modifier = Modifier.padding(vertical = 8.dp),
                    platforms = gameDetail.platforms
                )
            }
            if (!gameDetail.description.isNullOrBlank()) {
                TextTitleMedium(
                    text = stringResource(R.string.game_detail_description)
                )
                TextBodyMedium(
                    text = gameDetail.description
                )
            }
        }
    }
}

@Composable
fun RatingRow(game: GameDetail) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextTitleMedium(
            text = "${game.rating} / 5"
        )
        Spacer(modifier = Modifier.width(8.dp))
        repeat(game.rating.toInt()) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        repeat(5 - game.rating.toInt()) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        game.metacritic?.let {
            AssistChip(
                onClick = {

                },
                label = {
                    TextLabelMedium(
                        text = stringResource(R.string.game_detail_metacritic, it)
                    )
                }
            )
        }
    }
}

@Composable
fun MetadataRow(game: GameDetail) {
    Column {
        game.released?.let {
            TextLabelMedium(
                text = stringResource(R.string.game_detail_released, it)
            )
        }
        if (game.playtime > 0) {
            TextLabelMedium(
                text = stringResource(R.string.game_detail_playtime, game.playtime.toString())
            )
        }
        game.esrb?.let {
            TextLabelMedium(
                text = stringResource(R.string.game_detail_age_rating, it)
            )
        }
    }
}

@Composable
fun PlatformSection(
    modifier: Modifier = Modifier,
    platforms: List<String>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        TextTitleMedium(
            text = "Platforms"
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            platforms.forEach { platform ->
                AssistChip(
                    onClick = {

                    },
                    label = {
                        TextLabelMedium(
                            text = platform
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameDetailUiPreview() {
    GameDetailUi(
        gameDetailUiState = GameDetailUiState.Success(
            GameDetail(
                id = 1,
                name = "Game Name",
                description = "Game Description",
                released = "2022-12-31",
                image = "https://images.unsplash.com/photo-168",
                rating = 3.75,
                metacritic = 100,
                playtime = 10,
                platforms = listOf("PC", "PS5"),
                esrb = "E"
            )
        ),
        onBackClick = {}
    )
}