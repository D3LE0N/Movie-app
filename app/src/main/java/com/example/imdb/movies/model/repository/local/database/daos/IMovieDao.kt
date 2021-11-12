package com.example.imdb.movies.model.repository.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.imdb.movies.model.repository.local.database.entities.MovieEntity

@Dao
interface IMovieDao {

    @Query("SELECT * FROM movie ")
    fun getAll(): List<MovieEntity>?

    @Query("SELECT * FROM movie limit :size offset (:size * (:page - 1))")
    fun getPage(page: Int, size: Int = 10) : List<MovieEntity>?

    @Query("DELETE FROM movie")
    fun deleteAll()

    @Insert
    fun insertMovies(movieEntity: List<MovieEntity>)
}