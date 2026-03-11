package az.mamedali.rawg.home.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import az.mamedali.rawg.core.ui.components.TextLabelMedium
import az.mamedali.rawg.core.ui.components.TextTitleSmall
import az.mamedali.rawg.core.domain.Game
import coil3.compose.AsyncImage

@Composable
fun GameCard(
    modifier: Modifier = Modifier,
    game: Game,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    onClick(game.id)
                }
                .width(150.dp)
                .height(250.dp)
        ) {
            AsyncImage(
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.Crop,
                model = game.imageUrl,
                contentDescription = null
            )
            TextTitleSmall(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 8.dp)
                    .padding(horizontal = 8.dp),
                text = game.name,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )
            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(bottom = 8.dp)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextLabelMedium(
                    modifier = Modifier.weight(1f),
                    text = "${game.rating} / 5"
                )
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

@Preview
@Composable
fun GameCardPreview() {
    GameCard(
        game = Game(
            id = 1,
            name = "Game Name",
            imageUrl = "https://images.unsplash.com/photo-168",
            rating = 3.75
        ),
        onClick = {}
    )
}
