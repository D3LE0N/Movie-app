package com.example.imdb.movies.model

import com.example.imdb.movies.shared.Movie

interface IMoviesModel {

    suspend fun getNextMoviePage(): List<Movie>

    suspend fun search(query: String): List<Movie>
}