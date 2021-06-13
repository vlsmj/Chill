package com.blueberryprojects.chill

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Prepare the application for dependency injection purposes.
 */
@HiltAndroidApp
class ChillApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}