package com.example.demoprojectforrnd.customviews.ribbon

import android.content.res.Resources


internal fun Float.dp2px(resources: Resources): Int {
    val scale = resources.displayMetrics.density
    return (this * scale * 0.5f).toInt()
}