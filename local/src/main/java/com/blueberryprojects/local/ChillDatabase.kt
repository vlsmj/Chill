package com.blueberryprojects.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blueberryprojects.common.entities.Movie
import com.blueberryprojects.local.dao.MovieDao

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class ChillDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}