package com.example.imdb.movies.model.repository.local

import com.example.imdb.movies.model.tools.toMovie
import com.example.imdb.movies.model.tools.toMovieEntity
import com.example.imdb.movies.model.tools.toMovieEntityList
import com.example.imdb.movies.model.tools.toMovieList
import com.example.imdb.movies.shared.Movie
import com.example.imdb.shared.database.Database
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesLocalRepository @Inject constructor(
    private val database: Database
) :
    IMoviesLocalRepository {


    override suspend fun getMoviePage(page: Int): List<Movie> {

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

    override fun updateMovie(movie: Movie) {

        database
            .movieEntityDao()
            .addMovieToSeeLaterList(movie.toMovieEntity())
    }

    override fun getMoviesToSeeLater(): List<Movie> {

        val seeLaterMovies = database
            .movieEntityDao()
            .getSeeLaterMovies()

        return seeLaterMovies?.toMovieList() ?: emptyList()
    }

    override fun getFavoritesMovies(): List<Movie> {

        val favorites = database
            .movieEntityDao()
            .getFavorites()

        return favorites?.toMovieList() ?: emptyList()
    }
}