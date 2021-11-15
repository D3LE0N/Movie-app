package com.example.imdb.movies.shared

import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("poster_path")
    var poster: String?,
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("release_date")
    var releaseDate: String?,
    @SerializedName("vote_average")
    var voteAverage: Double,
    @SerializedName("backdrop_path")
    var backdrop: String?,
    var favorite: Boolean = false,
    var seeLater: Boolean = false
)
