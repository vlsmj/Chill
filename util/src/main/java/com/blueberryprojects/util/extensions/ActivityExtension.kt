package com.blueberryprojects.util.extensions

import android.app.Activity
import android.view.inputmethod.InputMethodManager

/**
 * Toggle the keyboard to show or hide.
 */
fun Activity.toggleKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}
