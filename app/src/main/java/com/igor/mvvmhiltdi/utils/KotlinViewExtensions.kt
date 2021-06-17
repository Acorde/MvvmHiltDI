package com.igor.mvvmhiltdi.utils

import android.view.View

fun View.show(show: Boolean): Pair<Boolean, View> {
    when (show) {
        true -> this.visibility = View.VISIBLE
        false -> this.visibility = View.GONE
    }
    return Pair(show, this)
}