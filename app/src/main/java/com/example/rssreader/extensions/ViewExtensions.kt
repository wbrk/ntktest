package com.example.rssreader.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.textfield.TextInputEditText

fun Activity.hideKeyboard(focusedView: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(focusedView.windowToken, 0)
}

fun Activity.showKeyboardForView(view: View) {
    view.requestFocus()
    view.post {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }
}

fun TextInputEditText.setSelectionToEnd() {
    setSelection(text?.length ?: 0)
}