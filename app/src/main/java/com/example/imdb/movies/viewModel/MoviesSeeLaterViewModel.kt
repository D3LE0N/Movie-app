package com.example.imdb.movies.viewModel

import androidx.lifecycle.ViewModel
import com.example.imdb.movies.model.IMoviesModel
import com.example.imdb.movies.shared.Movie
import com.example.imdb.movies.ui.fragments.adapter.MovieListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesSeeLaterViewModel @Inject constructor(private val model: IMoviesModel) : ViewModel() {

    val adapter = MovieListAdapter()


    suspend fun getSeeLaterMovies() {

        val movies = model.getSeeLaterMovies()

        withContext(Dispatchers.Main) {
            adapter.addMovies(movies)
        }
    }

    suspend fun removeFromToSeeLater(movie: Movie) {
        movie.seeLater = false
        model.updateMovie(movie)
        withContext(Dispatchers.Main) {
            adapter.removeMovie(movie)
        }
    }

    suspend fun addToFavorites(movie: Movie) {

        movie.favorite = true
        model.updateMovie(movie)
        withContext(Dispatchers.Main) {
            adapter.updatedMovie(movie)
        }
    }

    suspend fun removeFromFavorites(movie: Movie) {

        movie.favorite = false
        model.updateMovie(movie)
        withContext(Dispatchers.Main) {
            adapter.updatedMovie(movie)
        }
    }

    suspend fun addOrRemoveFromFavorites(movie: Movie) {

        if (movie.favorite) {
            removeFromFavorites(movie)
        } else {
            addToFavorites(movie)
        }
    }
}