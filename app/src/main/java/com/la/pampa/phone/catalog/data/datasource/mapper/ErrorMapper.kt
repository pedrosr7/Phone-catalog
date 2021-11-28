package com.la.pampa.phone.catalog.data.datasource.mapper

import com.la.pampa.phone.catalog.core.exceptions.MyFailure


fun Int.toNetworkError() = when (this) {
    401 -> MyFailure.Unauthorized
    else -> MyFailure.ServerError
}

fun Throwable.normalizeError() = when (this) {
    is MyFailure -> this
    else -> MyFailure.ServerError
}