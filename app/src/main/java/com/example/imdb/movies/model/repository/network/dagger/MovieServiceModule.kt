package com.example.imdb.movies.model.repository.network.dagger

import com.example.imdb.BuildConfig
import com.example.imdb.movies.model.repository.network.IRetrofitMovieService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MovieServiceModule {

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIES_SERVICE_BASE_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun getMovieService(retrofit: Retrofit): IRetrofitMovieService {
        return retrofit.create(IRetrofitMovieService::class.java)
    }
}