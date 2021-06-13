package com.blueberryprojects.util.extensions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment

/**
 * Launch browser and redirect to url.
 *
 * @param url The link to be searched in the browser.
 */
fun Fragment.launchWebsiteUrl(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
}

/**
 * Hide the action bar in Fragment.
 */
fun Fragment.hideActionBar() {
    activity?.let {
        if (it is AppCompatActivity) it.supportActionBar?.hide()
    }
}

/**
 * Hide the action bar in Fragment.
 */
fun Fragment.showActionBar() {
    activity?.let {
        if (it is AppCompatActivity) it.supportActionBar?.show()
    }
}

/**
 * Replace the action bar title.
 *
 * @param title The title to be used in the action bar.
 */

fun Fragment.setActionBarTitle(title: String) {
    activity?.let {
        if (it is AppCompatActivity) it.supportActionBar?.title = title
    }
}