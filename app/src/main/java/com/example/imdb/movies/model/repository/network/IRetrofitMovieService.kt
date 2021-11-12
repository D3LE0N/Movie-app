package com.example.imdb.movies.model.repository.network

import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofitMovieService {

    @GET("discover/movie")
    suspend fun getPage(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
        @Query("year") year: Int
    ): PaginationService
}