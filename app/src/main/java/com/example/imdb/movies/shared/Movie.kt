package com.example.imdb.movies.shared

import com.beust.klaxon.Json
import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("id")
    @Json(name = "id")
    var id: Int,
    @SerializedName("title")
    @Json(name = "title")
    var title: String,
    @SerializedName("poster_path")
    @Json(name = "poster_path")
    var poster: String,
    @SerializedName("overview")
    @Json(name = "overview")
    var overview: String,
    @SerializedName("release_date")
    @Json(name = "release_date")
    var releaseDate: String,
    @SerializedName("vote_average")
    @Json(name = "vote_average")
    var voteAverage: Double,
    @SerializedName("backdrop_path")
    @Json(name = "backdrop_path")
    var backdrop: String,
    var favorite: Boolean = true
)
