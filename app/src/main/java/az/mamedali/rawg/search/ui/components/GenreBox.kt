package az.mamedali.rawg.search.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import az.mamedali.rawg.core.ui.TextTitleMedium
import az.mamedali.rawg.search.domain.Genre
import coil3.compose.AsyncImage

@Composable
fun GenreBox(
    modifier: Modifier = Modifier,
    genre: Genre
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clickable {

            },
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = genre.imageBackground,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            alpha = 0.5f
        )
        TextTitleMedium(
            text = genre.name
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GenreBoxPreview() {
    GenreBox(
        genre = Genre(
            name = "Action",
            slug = "",
            imageBackground = "https://images.unsplash.com/photo-168",
            gamesCount = 10
        )
    )
}