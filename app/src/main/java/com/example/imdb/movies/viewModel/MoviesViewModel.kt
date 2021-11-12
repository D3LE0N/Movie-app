package com.example.imdb.movies.viewModel

import androidx.lifecycle.ViewModel
import com.example.imdb.movies.model.IMoviesModel
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val model: IMoviesModel
) : ViewModel() {


}