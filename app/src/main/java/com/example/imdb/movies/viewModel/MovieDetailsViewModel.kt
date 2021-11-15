package com.example.imdb.movies.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb.movies.shared.Movie
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor() : ViewModel() {

    val movie = MutableLiveData<Movie?>()
}