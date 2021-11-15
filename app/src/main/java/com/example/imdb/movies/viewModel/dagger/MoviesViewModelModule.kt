package com.example.imdb.movies.viewModel.dagger

import com.example.imdb.movies.model.IMoviesModel
import com.example.imdb.movies.viewModel.MoviesViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviesViewModelModule {

    @Provides
    fun getMoviesViewModel(model: IMoviesModel): MoviesViewModel {

        return MoviesViewModel(model)
    }
}