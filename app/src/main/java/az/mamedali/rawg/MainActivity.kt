package az.mamedali.rawg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import az.mamedali.rawg.core.ui.RawgTheme
import az.mamedali.rawg.home.ui.HomeScreen
import androidx.navigation.compose.composable
import az.mamedali.rawg.core.ui.Route
import az.mamedali.rawg.core.ui.BottomNavItem
import az.mamedali.rawg.core.ui.BottomNavigationBar
import az.mamedali.rawg.favorites.ui.FavoritesScreen
import az.mamedali.rawg.game_detail.ui.GameDetailScreen
import az.mamedali.rawg.search.ui.SearchScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RawgTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = stringResource(R.string.destination_home),
                                    route = Route.Home,
                                    icon = Icons.Outlined.Home,
                                    selectedIcon = Icons.Filled.Home
                                ),
                                BottomNavItem(
                                    name = stringResource(R.string.destination_search),
                                    route = Route.Search,
                                    icon = Icons.Outlined.Search,
                                    selectedIcon = Icons.Filled.Search
                                ),
                                BottomNavItem(
                                    name = stringResource(R.string.destination_favorites),
                                    route = Route.Favorites,
                                    icon = Icons.Outlined.FavoriteBorder,
                                    selectedIcon = Icons.Filled.Favorite
                                )
                            ),
                            navController = navController
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Route.Home,
                        enterTransition = { EnterTransition.None },
                        exitTransition = { ExitTransition.None }
                    ) {
                        composable<Route.Home> {
                            HomeScreen(
                                onGameClick = { gameId ->
                                    navController.navigate(Route.GameDetail(gameId))
                                }
                            )
                        }
                        composable<Route.Search> {
                            SearchScreen()
                        }
                        composable<Route.Favorites> {
                            FavoritesScreen()
                        }
                        composable<Route.GameDetail> {
                            GameDetailScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}