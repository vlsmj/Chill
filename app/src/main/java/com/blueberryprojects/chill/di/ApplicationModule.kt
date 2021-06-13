package com.blueberryprojects.chill.di

import android.app.Application
import androidx.room.Room
import com.blueberryprojects.local.ChillDatabase
import com.blueberryprojects.network.service.ApiService
import com.blueberryprojects.util.PrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Prepare the single instance application module components to be used
 * across the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    /**
     * Provide the Room database instance.
     *
     * @param application The Application instance.
     */
    @Provides
    fun providesChillDatabase(application: Application): ChillDatabase =
        Room.databaseBuilder(application, ChillDatabase::class.java, "chill_db")
            .fallbackToDestructiveMigration()
            .build()

    /**
     * Provide the API service with the corresponding endpoints
     * from iTunes public API.
     */
    @Provides
    fun providesApiService(): ApiService {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    /**
     * Provide the shared preference instance.
     *
     * @param application The application instance.
     */
    @Provides
    fun providesPrefManager(application: Application): PrefManager = PrefManager(application)
}