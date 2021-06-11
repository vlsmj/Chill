package com.blueberryprojects.repository

import com.blueberryprojects.local.ChillDatabase
import com.blueberryprojects.network.service.ApiService
import com.blueberryprojects.repository.fragment.HomeFragmentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Prepare the single instance repository module components to be used
 * across the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * Provide the Home Fragment Repository
     *
     * @param db The instahce of the Room database.
     * @param apiService The service with the API end points from
     * iTunes public API.
     */
    @Provides
    fun providesHomeFragmentRepository(db: ChillDatabase, apiService: ApiService) =
        HomeFragmentRepository(
            db,
            apiService
        )
}