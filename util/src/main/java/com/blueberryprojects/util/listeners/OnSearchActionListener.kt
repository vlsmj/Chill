package com.blueberryprojects.util.listeners

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView

/**
 * The EditText listener class for its actions.
 *
 * @param onSearch lambda function to get input data from the EditText.
 */
class OnSearchActionListener(
    private val onSearch: (movieName: String) -> Unit = {}
) :
    TextView.OnEditorActionListener {

    /**
     * Get the input text after pressing the search icon from the EditText.
     */
    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        return if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            onSearch(v?.text.toString().trim())
            true
        } else false
    }
}