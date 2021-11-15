package com.example.imdb.movies.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb.movies.model.IMoviesModel
import com.example.imdb.movies.shared.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val model: IMoviesModel
) : ViewModel() {


    val loading = MutableLiveData(false)

    val movieList = MutableLiveData<List<Movie>>(mutableListOf())

    suspend fun getNextPage() {

        withContext(Dispatchers.Main) {
            loading.value = true
        }

        val moviePage = model.getNextMoviePage()

        withContext(Dispatchers.Main) {
            loading.value = false
            movieList.value = moviePage
        }

    }
}