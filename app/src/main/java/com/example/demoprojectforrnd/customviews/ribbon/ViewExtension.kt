package com.example.demoprojectforrnd.customviews.ribbon

import android.view.View

/** rotate view between 1 to 90 or -90 to -1. */
internal fun View.rotation(degree: Int) {
    if (degree in 1..90) {
        pivotX = 1.0f
        pivotY = 0.5f
        x += measuredWidth / 2
        y -= measuredWidth / 4 - measuredWidth / 16
    } else if (degree in -90..-1) {
        pivotX = 0.5f
        pivotY = 1.0f
        x -= measuredWidth / 4 - measuredWidth / 16
        y += measuredWidth / 2
    }

    rotation = degree.toFloat()
}