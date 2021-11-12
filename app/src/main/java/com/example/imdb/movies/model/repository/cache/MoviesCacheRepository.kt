package com.example.imdb.movies.model.repository.cache

import com.example.imdb.movies.shared.Movie
import javax.inject.Inject

class MoviesCacheRepository @Inject constructor() : IMoviesCacheRepository {

    private var _moviesInMemory = mutableMapOf<Int, List<Movie>?>()

    override suspend fun getMoviePage(page: Int): List<Movie>? = _moviesInMemory[page]

    override suspend fun saveMovieList(page: Int, movies: List<Movie>) {
        _moviesInMemory[page] = movies
    }
}