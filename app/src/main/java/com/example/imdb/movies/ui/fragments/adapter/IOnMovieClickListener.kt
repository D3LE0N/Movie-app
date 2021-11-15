package com.example.imdb.movies.ui.fragments.adapter

import android.view.View
import com.example.imdb.movies.shared.Movie

interface IOnMovieClickListener {

    fun movieClicked(movie: Movie, view: View? = null)
}