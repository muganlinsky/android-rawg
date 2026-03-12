package az.mamedali.rawg.games_by_genre.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import az.mamedali.rawg.core.domain.Game
import az.mamedali.rawg.core.ui.components.TextBodyMedium
import az.mamedali.rawg.core.ui.components.TextTitleSmall
import coil3.compose.AsyncImage

@Composable
fun GameRow(
    modifier: Modifier = Modifier,
    game: Game,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(game.id)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                model = game.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                TextTitleSmall(
                    text = game.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    TextBodyMedium(
                        text = game.rating.toString()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun GameRowPreview() {
    GameRow(
        game = Game(
            id = 1,
            name = "Game name",
            rating = 4.5,
            imageUrl = "https://images.unsplash.com/photo-168"
        ),
        onClick = {}
    )
}