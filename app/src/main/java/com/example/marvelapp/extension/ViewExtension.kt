package com.example.marvelapp.extension

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    val inputMethodManager =
        requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = requireActivity().currentFocus
    if (view == null) {
        view = View(requireContext())
    }

    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}