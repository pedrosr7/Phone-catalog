package com.la.pampa.phone.catalog.di

import com.la.pampa.phone.catalog.MyApplication
import org.koin.dsl.module

val appModule = module {
    single { MyApplication() }
}

