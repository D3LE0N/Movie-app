package com.example.imdb.movies.model.repository.local.dagger

import android.content.Context
import androidx.room.Room
import com.example.imdb.movies.model.repository.local.IMoviesLocalRepository
import com.example.imdb.movies.model.repository.local.MoviesLocalRepository
import com.example.imdb.shared.database.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MovieLocalRepositoryModule {

    @Provides
    @Singleton
    fun getDatabase(context: Context): Database {

        return Room.databaseBuilder(
            context,
            Database::class.java, "moviesDatabase"
        ).build()
    }
}