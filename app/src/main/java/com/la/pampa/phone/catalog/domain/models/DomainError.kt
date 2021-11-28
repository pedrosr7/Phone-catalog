package com.la.pampa.phone.catalog.domain.models

import com.la.pampa.phone.catalog.core.exceptions.DataPreferencesError
import com.la.pampa.phone.catalog.core.exceptions.MyFailure

sealed class DomainError {

    companion object {
        fun fromThrowable(e: Throwable): DomainError =
            when (e) {
                is MyFailure.NotFound -> NotFoundError
                is MyFailure.Unauthorized -> AuthenticationError
                is MyFailure.NetworkConnectionError -> NetworkConnectionError
                is DataPreferencesError.NotFoundData -> NotFoundDataError
                else ->NotFoundDataError// UnknownServerError((Some(e)))
            }
    }

    object NetworkConnectionError : DomainError()
    object AuthenticationError : DomainError()
    object NotFoundDataError : DomainError()
    object NotFoundError : DomainError()
    //data class UnknownServerError(val e: Option<Throwable> = None) : DomainError()
}