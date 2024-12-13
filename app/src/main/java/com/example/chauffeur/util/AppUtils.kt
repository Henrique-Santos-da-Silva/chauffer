package com.example.chauffeur.util

import android.view.View

fun View.showProgress(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}
