package com.example.imdb.movies.model.repository.network

import com.example.imdb.movies.shared.Movie
import com.google.gson.annotations.SerializedName

data class PaginationService(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int
)
