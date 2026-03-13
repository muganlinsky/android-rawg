package az.mamedali.rawg.search.data

import az.mamedali.rawg.core.domain.network.NetworkError
import az.mamedali.rawg.core.domain.network.Result
import az.mamedali.rawg.search.domain.SearchRepository

class SearchRepositoryImpl(
    private val remoteDataSource: GenresRemoteDataSource,
    private val localDataSource: GenresLocalDataSource
): SearchRepository {
    override suspend fun fetchGenres(): Result<GenreResponse, NetworkError> {
        val localGenres = localDataSource.fetchGenres().sortedBy { it.name }
        if (localGenres.isNotEmpty()) {
            val genres = GenreResponse(localGenres.map { it.toGenre() })
            return Result.Success(genres)
        }
        val remoteGenres = remoteDataSource.fetchGenres()
        if (remoteGenres is Result.Success) {
            val genres = remoteGenres.data.results.map { it.toGenreEntity() }.sortedBy { it.name }
            localDataSource.saveGenres(genres)
        }
        return remoteGenres
    }
}