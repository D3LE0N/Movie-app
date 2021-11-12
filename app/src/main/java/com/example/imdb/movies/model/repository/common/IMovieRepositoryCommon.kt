package com.example.imdb.movies.model.repository.common

import com.example.imdb.movies.shared.Movie

interface IMovieRepositoryCommon {

    suspend fun getMoviePage(page: Int): List<Movie>?

    suspend fun saveMovieList(page: Int, movies: List<Movie>)
}