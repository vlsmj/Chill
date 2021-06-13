package com.blueberryprojects.chill

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.blueberryprojects.chill.databinding.ActivityMainBinding
import com.blueberryprojects.util.PrefManager
import com.blueberryprojects.util.TimeUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /**
     * Inject shared preference for saving last visit time of home screen.
     *
     * @return Returns a reference of shared preference.
     */
    @Inject
    lateinit var prefManager: PrefManager

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        setupActionBarWithNavController(navController)
    }

    /**
     * Listen to on back press from fragment to get back to previous screen.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Here we save the current date and time for later use if the activity
     * is about to be destroyed or not on the foreground.
     *
     */
    override fun isFinishing(): Boolean {
        prefManager.lastTimeChecked = TimeUtils.getCurrentDateAndTime()
        return super.isFinishing()
    }
}