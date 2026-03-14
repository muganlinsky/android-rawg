package az.mamedali.rawg.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import az.mamedali.rawg.R
import az.mamedali.rawg.core.ui.components.TextHeadlineSmall
import az.mamedali.rawg.core.ui.components.TextTitleLarge
import az.mamedali.rawg.search.domain.Genre
import az.mamedali.rawg.search.ui.components.GenreBox
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen(
    onGenreClick: (Int, String) -> Unit,
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    SearchUi(
        genres = uiState.genres,
        onGenreClick = onGenreClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchUi(
    modifier: Modifier = Modifier,
    genres: List<Genre> = emptyList(),
    onGenreClick: (Int, String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                TextTitleLarge(
                    text = stringResource(R.string.search_title)
                )
            }
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(genres) { genre ->
                GenreBox(
                    genre = genre,
                    onClick = onGenreClick
                )
            }
        }
    }
}