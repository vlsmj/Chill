package com.blueberryprojects.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blueberryprojects.common.entities.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    /**
     * Get all the movies currently saved in the database.
     */
    @Query("SELECT * FROM movie")
    fun getMovies(): Flow<List<Movie>>

    /**
     * Insert new set of movies. Replace if conflicts occur.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    /**
     * Delete all the movies in the database.
     */
    @Query("DELETE FROM movie")
    suspend fun deleteMovies()
}