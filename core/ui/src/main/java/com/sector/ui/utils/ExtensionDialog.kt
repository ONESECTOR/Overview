package com.sector.ui.utils

import android.app.Dialog
import android.view.ViewGroup

/* Позволяет выставлять размеры диалога (в данном случае ширины) в Int
*  Введённое число переводится в проценты, поэтому, можно сказать, вы задаёте ширину сразу в процентах
*  Пример: передаётся число 99, оно переводится в 0,99, что означает 99%
* */

fun Dialog.setLayoutWidth(width: Int) {
    val metrics = context.resources.displayMetrics
    val calculatedWidth = (metrics.widthPixels * (width * 0.01)).toInt()

    this.window?.setLayout(
        calculatedWidth,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
}

/* Позволяет выставлять размеры диалога (в данном случае высоты) в Int
*  Введённое число переводится в проценты, поэтому, можно сказать, вы задаёте ширину сразу в процентах
*  Пример: передаётся число 99, оно переводится в 0,99, что означает 99%
* */

fun Dialog.setLayoutHeight(height: Int) {
    val metrics = context.resources.displayMetrics
    val calculatedHeight = (metrics.heightPixels * (height * 0.01)).toInt()

    this.window?.setLayout(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        calculatedHeight
    )
}

/* Позволяет выставлять размеры диалога (в данном случае и высоты и ширины одновременно) в Int
*  Введённое число переводится в проценты, поэтому, можно сказать, вы задаёте ширину сразу в процентах
*  Пример: передаётся число 99, оно переводится в 0,99, что означает 99%
* */

fun Dialog.setLayoutDimensions(width: Int, height: Int) {
    val metrics = context.resources.displayMetrics
    val calculatedWidth = (metrics.widthPixels * (width * 0.01)).toInt()
    val calculatedHeight = (metrics.heightPixels * (height * 0.01)).toInt()

    this.window?.setLayout(
        calculatedWidth,
        calculatedHeight
    )
}