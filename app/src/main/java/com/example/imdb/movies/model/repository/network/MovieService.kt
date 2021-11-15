package com.example.imdb.movies.model.repository.network

import android.util.Log
import com.example.imdb.BuildConfig
import javax.inject.Inject


class MovieService @Inject constructor(private val service: IRetrofitMovieService) :
    IMoviesService {

    override suspend fun getPage(page: Int, quantityPerPage: Int): PaginationService? {

        try {

            return service.getPage(page, BuildConfig.API_KEY, 2021, quantityPerPage)

        } catch (e: Exception) {
            Log.e("error to fetch movies", e.message ?: "No catch error en message")
        }

        return null
    }
}