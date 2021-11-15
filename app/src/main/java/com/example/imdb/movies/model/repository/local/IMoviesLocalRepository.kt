package com.example.imdb.movies.model.repository.local

import com.example.imdb.movies.model.repository.common.IMovieRepositoryCommon
import com.example.imdb.movies.shared.Movie

interface IMoviesLocalRepository : IMovieRepositoryCommon {

    fun updateMovie(movie: Movie)

    fun getMoviesToSeeLater(): List<Movie>

    fun getFavoritesMovies(): List<Movie>
}