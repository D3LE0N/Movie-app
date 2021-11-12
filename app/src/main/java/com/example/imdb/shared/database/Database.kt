package com.example.imdb.shared.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imdb.movies.model.repository.local.database.daos.IMovieDao
import com.example.imdb.movies.model.repository.local.database.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun movieEntityDao(): IMovieDao
}