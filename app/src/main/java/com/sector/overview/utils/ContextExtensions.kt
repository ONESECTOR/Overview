package com.sector.overview.utils

import android.content.Context
import android.util.TypedValue

fun Context.spToPx(sp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, this.resources.displayMetrics)
}