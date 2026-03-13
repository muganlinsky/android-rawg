package az.mamedali.rawg.koin

import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import az.mamedali.rawg.game_detail.data.GameDetailRepositoryImpl
import az.mamedali.rawg.game_detail.domain.GameDetailRepository
import az.mamedali.rawg.game_detail.domain.GetGameDetailUseCase
import az.mamedali.rawg.game_detail.ui.GameDetailViewModel
import az.mamedali.rawg.home.data.HomeRepositoryImpl
import az.mamedali.rawg.home.domain.GetAllGamesUseCase
import az.mamedali.rawg.home.domain.GetTrendingGamesUseCase
import az.mamedali.rawg.home.domain.HomeRepository
import az.mamedali.rawg.home.ui.HomeViewModel
import az.mamedali.rawg.search.data.SearchRepositoryImpl
import az.mamedali.rawg.search.domain.GetGenresUseCase
import az.mamedali.rawg.search.domain.SearchRepository
import az.mamedali.rawg.search.ui.SearchViewModel
import az.mamedali.rawg.BuildConfig
import az.mamedali.rawg.games_by_genre.data.GamesByGenreRepositoryImpl
import az.mamedali.rawg.games_by_genre.domain.GamesByGenreRepository
import az.mamedali.rawg.games_by_genre.ui.GamesByGenreViewModel
import az.mamedali.rawg.room.AppDatabase
import az.mamedali.rawg.search.data.GenresLocalDataSource
import az.mamedali.rawg.search.data.GenresRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
val appModules = module {
    single {
        HttpClient(OkHttp.create()) {
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.rawg.io"
                    parameters.append("key", BuildConfig.RAWG_API_KEY)
                }
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        namingStrategy = JsonNamingStrategy.SnakeCase
                    }
                )
            }
        }
    }

    single {
        Room.databaseBuilder(
            context = androidApplication(),
            klass = AppDatabase::class.java,
            name = "genres-db"
        )
            .build()
    }
    single {
        get<AppDatabase>().genresDao()
    }

    singleOf(::GenresLocalDataSource)
    singleOf(::GenresRemoteDataSource)

    singleOf(::HomeRepositoryImpl) {
        bind<HomeRepository>()
    }
    singleOf(::SearchRepositoryImpl) {
        bind<SearchRepository>()
    }
    singleOf(::GameDetailRepositoryImpl) {
        bind<GameDetailRepository>()
    }
    singleOf(::GamesByGenreRepositoryImpl) {
        bind<GamesByGenreRepository>()
    }

    singleOf(::GetTrendingGamesUseCase)
    singleOf(::GetAllGamesUseCase)
    singleOf(::GetGenresUseCase)
    singleOf(::GetGameDetailUseCase)

    viewModelOf(::HomeViewModel)
    viewModelOf(::SearchViewModel)
    viewModel { (savedStateHandle: SavedStateHandle) ->
        GameDetailViewModel(
            savedStateHandle = savedStateHandle,
            getGameDetailUseCase = get()
        )
    }
    viewModel { (savedStateHandle: SavedStateHandle) ->
        GamesByGenreViewModel(
            savedStateHandle = savedStateHandle,
            repository = get()
        )
    }
}