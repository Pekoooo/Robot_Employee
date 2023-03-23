package com.example.robotemployee.extensions

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String, length: Int = Snackbar.LENGTH_LONG) {

    val snackBar = Snackbar.make(this, message, length)

    snackBar.show()

}

fun View.showSnackBar(@StringRes message: Int, length: Int = Snackbar.LENGTH_LONG) {

    val snackBar = Snackbar.make(this, message, length)

    snackBar.show()

}