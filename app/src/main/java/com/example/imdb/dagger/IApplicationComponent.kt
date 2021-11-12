package com.example.imdb.dagger

import android.content.Context
import com.example.imdb.movies.model.dagger.MoviesModelBindModule
import com.example.imdb.movies.model.repository.cache.dagger.MoviesCacheModule
import com.example.imdb.movies.model.repository.local.dagger.MovieLocalRepositoryBindModule
import com.example.imdb.movies.model.repository.local.dagger.MovieLocalRepositoryModule
import com.example.imdb.movies.model.repository.network.dagger.MovieServiceBindModule
import com.example.imdb.movies.model.repository.network.dagger.MovieServiceModule
import com.example.imdb.movies.ui.fragments.MovieListFragment
import com.example.imdb.movies.viewModel.dagger.MoviesViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MovieServiceModule::class,
        MovieLocalRepositoryModule::class,
        MovieLocalRepositoryBindModule::class,
        MoviesCacheModule::class,
        MoviesModelBindModule::class,
        MoviesViewModelModule::class,
        MovieServiceBindModule::class]
)
interface IApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): IApplicationComponent
    }

    fun inject(movieFragment: MovieListFragment)
}