package com.example.imdb.movies.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb.movies.model.IMoviesModel
import com.example.imdb.movies.shared.Movie
import com.example.imdb.movies.ui.fragments.adapter.MovieListAdapter
import com.example.imdb.movies.ui.fragments.adapter.MovieSearchResultAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val model: IMoviesModel
) : ViewModel() {


    val loading = MutableLiveData(false)
    val adapter = MovieListAdapter()
    val searchResultAdapter = MovieSearchResultAdapter()
    val searchVisible = MutableLiveData(false)
    private var searching: Boolean = false

    suspend fun getNextPage() {

        withContext(Dispatchers.Main) {
            loading.value = true
        }

        val moviePage = model.getNextMoviePage()

        withContext(Dispatchers.Main) {
            loading.value = false
            adapter.addMovies(moviePage)

        }

    }

    suspend fun search(query: String) {

        if (searchVisible.value?.not() == true) {
            withContext(Dispatchers.Main) {
                searchVisible.value = true
            }
        }

        if (searching)
            return

        val page = model.search(query)
        withContext(Dispatchers.Main) {
            searchResultAdapter.replaceMovies(page)
        }
    }

    suspend fun addToSeeLater(movie: Movie) {

        movie.seeLater = true
        model.updateMovie(movie)
        withContext(Dispatchers.Main) {
            adapter.updatedMovie(movie)
        }
    }

    suspend fun removeFromToSeeLater(movie: Movie) {
        movie.seeLater = false
        model.updateMovie(movie)
        withContext(Dispatchers.Main) {
            adapter.updatedMovie(movie)
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