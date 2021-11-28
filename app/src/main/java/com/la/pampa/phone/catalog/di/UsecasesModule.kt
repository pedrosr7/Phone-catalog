package com.la.pampa.phone.catalog.di

import com.la.pampa.phone.catalog.domain.usecases.GetPhonesUseCases
import com.la.pampa.phone.catalog.domain.usecases.SavePhoneUseCases
import org.koin.dsl.module

val useCaseModule = module {
    single { GetPhonesUseCases(get()) }
    single { SavePhoneUseCases(get()) }
}
