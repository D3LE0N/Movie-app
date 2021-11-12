package com.example.imdb.movies.model.repository.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val poster: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val backdrop: String,
    val favorite: Boolean
)
