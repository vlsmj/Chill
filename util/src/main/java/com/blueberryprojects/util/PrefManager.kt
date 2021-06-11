package com.blueberryprojects.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.blueberryprojects.util.TimeUtils.getCurrentDateAndTime

/**
 * The class of shared preferences to be used across
 * the application.
 */
class PrefManager(
    private val application: Application
) {

    companion object {
        const val PREF_USER = "PREF_USER"
        const val PREF_LAST_TIME_CHECKED = "PREF_LAST_TIME_CHECKED"
    }

    /**
     * Get the instance of the shared preference.
     */
    private fun getSharedPreferences(): SharedPreferences {
        return application.getSharedPreferences(PREF_USER, Context.MODE_PRIVATE)
    }

    /**
     * Get and Set the last time visited.
     */
    var lastTimeChecked: String?
        get() = getSharedPreferences().getString(
            PREF_LAST_TIME_CHECKED, getCurrentDateAndTime()
        )
        set(value) {
            getSharedPreferences().edit {
                putString(PREF_LAST_TIME_CHECKED, value)
            }
        }
}