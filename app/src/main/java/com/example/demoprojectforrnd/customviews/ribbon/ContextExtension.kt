package com.example.demoprojectforrnd.customviews.ribbon

import android.content.Context
import android.os.Build
import android.util.TypedValue
import com.example.demoprojectforrnd.R

/** gets an accent color. */
internal fun Context.accentColor(): Int {
    val colorAttr: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        R.attr.colorAccent
    } else {
        resources.getIdentifier("colorAccent", "attr", packageName)
    }
    val outValue = TypedValue()
    theme.resolveAttribute(colorAttr, outValue, true)
    return outValue.data
}