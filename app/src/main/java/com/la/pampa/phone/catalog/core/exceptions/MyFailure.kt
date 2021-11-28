package com.la.pampa.phone.catalog.core.exceptions

sealed class MyFailure: Throwable() {
    object InvalidObject: MyFailure()
    object NotFound: MyFailure()
    object ServerError: MyFailure()
    object Unauthorized: MyFailure()
    object NetworkConnectionError: MyFailure()
}