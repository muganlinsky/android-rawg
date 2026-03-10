package az.mamedali.rawg.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.mamedali.rawg.R
import az.mamedali.rawg.core.ui.TextHeadlineSmall
import az.mamedali.rawg.core.ui.TextTitleMedium
import az.mamedali.rawg.search.domain.Genre
import az.mamedali.rawg.search.ui.components.GenreBox
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    SearchUi(
        genres = uiState.genres
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchUi(
    modifier: Modifier = Modifier,
    genres: List<Genre> = emptyList()
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                TextHeadlineSmall(
                    text = stringResource(R.string.search_title)
                )
            }
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(genres) { genre ->
                GenreBox(
                    genre = genre
                )
            }
        }
    }
}