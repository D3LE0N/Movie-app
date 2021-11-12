package com.example.imdb.movies.model.repository.local

import com.example.imdb.movies.model.tools.toMovie
import com.example.imdb.movies.model.tools.toMovieEntityList
import com.example.imdb.movies.shared.Movie
import com.example.imdb.shared.database.Database
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesLocalRepository @Inject constructor(
    private val database: Database
) :
    IMoviesLocalRepository {

    override suspend fun getMoviePage(page: Int): List<Movie>? {

        val movies = database.movieEntityDao().getPage(page)

        val result = mutableListOf<Movie>()
        movies?.let {
            for (movie in it)
                result.add(movie.toMovie())
        }

        return result
    }

    override suspend fun saveMovieList(page: Int, movies: List<Movie>) {

        database
            .movieEntityDao()
            .insertMovies(movies.toMovieEntityList())
    }
}