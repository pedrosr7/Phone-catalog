package com.la.pampa.phone.catalog.di

import com.la.pampa.phone.catalog.presentation.FormPhoneViewModel
import com.la.pampa.phone.catalog.presentation.PhoneViewModel
import org.koin.dsl.module

val viewModelsModule = module {
    single { PhoneViewModel(get()) }
    single { FormPhoneViewModel(get()) }
}