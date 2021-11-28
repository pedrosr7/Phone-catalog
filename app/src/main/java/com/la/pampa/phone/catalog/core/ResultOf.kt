package com.la.pampa.phone.catalog.core

sealed class ResultOf<out T> {
    data class Success<out R>(val value: R): ResultOf<R>()
    data class Failure(
        val message: String? = null,
        val throwable: Throwable? = null
    ): ResultOf<Nothing>()
    object InProgress : ResultOf<Nothing>()
}