package ua.glorians.ekreative.test.app.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun showSnack(view: View?, message: String) {
    view?.let {Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()}
}