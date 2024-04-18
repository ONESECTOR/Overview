package com.sector.core.utils

class ReadOnlyOnceProperty<T>(
    private val initialValue: T,
    private var isRead: Boolean = false
) {
    fun get(): T? {
        if (isRead) return null
        isRead = true
        return initialValue
    }
}