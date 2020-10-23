package com.andreamw96.andreamettawijaya.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun showSnackBarWithAction(view: View, message: String, actionString: CharSequence, action: () -> Unit) {
    Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
        .setAction(actionString) {
            action()
        }
        .show()
}