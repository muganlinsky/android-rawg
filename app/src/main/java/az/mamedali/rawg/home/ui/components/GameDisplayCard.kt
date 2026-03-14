package az.mamedali.rawg.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import az.mamedali.rawg.core.ui.components.TextLabelMedium
import az.mamedali.rawg.core.domain.Game
import az.mamedali.rawg.core.ui.components.TextTitleLarge
import coil3.compose.AsyncImage

@Composable
fun GameDisplayCard(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    hasError: Boolean = false,
    game: Game? = null,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .clickable { game?.let { onClick(it.id) } }
        ) {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                hasError -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.BrokenImage,
                            contentDescription = "Image failed"
                        )
                    }
                }
                else -> {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(225.dp),
                        model = game?.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
                    ) {
                        TextTitleLarge(
                            text = game?.name.orEmpty()
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextLabelMedium(
                                text = "${game?.rating} / 5"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            if (game != null) {
                                Row {
                                    repeat(game.rating.toInt()) {
                                        Icon(
                                            modifier = Modifier.size(16.dp),
                                            imageVector = Icons.Default.Star,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                    repeat(5 - game.rating.toInt()) {
                                        Icon(
                                            modifier = Modifier.size(16.dp),
                                            imageVector = Icons.Default.Star,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GameDisplayCardPreview() {
    GameDisplayCard(
        isLoading = false,
        hasError = false,
        game = Game(
            id = 1,
            name = "Game Name",
            imageUrl = "",
            rating = 3.3
        ),
        onClick = {}
    )
}