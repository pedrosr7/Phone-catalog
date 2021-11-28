package com.la.pampa.phone.catalog.core.extensions

import com.la.pampa.phone.catalog.core.ResultOf

inline fun <reified T> ResultOf<T>.doIfFailure(callback: (error: String?, throwable: Throwable?) -> Unit) {
    if (this is ResultOf.Failure) {
        callback(message, throwable)
    }
}

inline fun <reified T> ResultOf<T>.doIfSuccess(callback: (value: T) -> Unit) {
    if (this is ResultOf.Success) {
        callback(value)
    }
}

/*
inline fun <reified T> ResultOf<T>.doIfInProgress(callback: () -> Unit) {
    if (this is ResultOf.InProgress) {
        callback()
    }
}*/
