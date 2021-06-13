package com.blueberryprojects.repository.fragment

import androidx.room.withTransaction
import com.blueberryprojects.local.ChillDatabase
import com.blueberryprojects.network.service.ApiService
import com.blueberryprojects.network.service.networkBoundResource
import javax.inject.Inject

/**
 * Repository for the movies.
 *
 * @param db The instahce of the Room database.
 * @param apiService The service with the API end points from
 * iTunes public API.
 */
class HomeFragmentRepository @Inject constructor(
    private val db: ChillDatabase,
    private val apiService: ApiService
) {
    /**
     * Instantiate the Movie DAO from the database.
     */
    private val movieDao = db.movieDao()

    /**
     * Get the movies list using service that handles the fetching
     * and saving data to the Room database.
     */
    fun getMovies(movieName: String) = networkBoundResource(
        query = { movieDao.getMovies() },
        fetch = {
            apiService.searchMovie(movieName)
        },
        saveFetchResult = { response ->
            db.withTransaction {
                movieDao.deleteMovies()
                movieDao.insertMovies(response.results)
            }
        }
    )
}