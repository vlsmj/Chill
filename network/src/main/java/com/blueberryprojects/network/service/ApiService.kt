package com.blueberryprojects.network.service

import com.blueberryprojects.common.entities.Movie
import com.blueberryprojects.network.wrapper.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * REST API access points
 */
interface ApiService {

    @GET("search")
    suspend fun searchMovie(
        @Query("term") movieName: String,
        @Query("country") country: String? = "au",
        @Query("media") media: String? = "movie"
    ): ApiResponse<List<Movie>>
}