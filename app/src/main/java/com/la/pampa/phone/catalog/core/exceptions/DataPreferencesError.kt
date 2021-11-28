package com.la.pampa.phone.catalog.core.exceptions

sealed class DataPreferencesError : Throwable() {
    object NotFoundData : DataPreferencesError()
}