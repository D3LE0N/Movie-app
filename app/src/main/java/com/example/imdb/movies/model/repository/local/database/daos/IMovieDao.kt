package com.example.imdb.movies.model.repository.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.imdb.movies.model.repository.local.database.entities.MovieEntity

@Dao
interface IMovieDao {

    @Query("SELECT * FROM movie ")
    fun getAll(): List<MovieEntity>?

    @Query("SELECT * FROM movie limit :size offset (:size * (:page - 1))")
    fun getPage(page: Int, size: Int = 10): List<MovieEntity>?

    @Query("DELETE FROM movie")
    fun deleteAll()

    @Insert(onConflict = REPLACE)
    fun insertMovies(movieEntity: List<MovieEntity>)

    @Update
    fun addMovieToSeeLaterList(movie: MovieEntity)

    @Query("SELECT * FROM movie WHERE seeLater = 1")
    fun getSeeLaterMovies(): List<MovieEntity>?

    @Query("SELECT * FROM movie where favorite = 1")
    fun getFavorites(): List<MovieEntity>?
}