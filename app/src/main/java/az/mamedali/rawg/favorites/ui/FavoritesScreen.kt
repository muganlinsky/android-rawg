package az.mamedali.rawg.favorites.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import az.mamedali.rawg.R
import az.mamedali.rawg.core.ui.components.TextHeadlineSmall
import az.mamedali.rawg.core.ui.components.TextTitleLarge

@Composable
fun FavoritesScreen() {
    FavoritesUi()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesUi(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                TextTitleLarge(
                    text = stringResource(R.string.favorites_title)
                )
            }
        )
    }
}