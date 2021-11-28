package com.la.pampa.phone.catalog

import android.app.Application
import android.content.Context
import com.la.pampa.phone.catalog.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate(){
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                appModule,
                viewModelsModule,
                useCaseModule,
                repositoryModule,
                phoneDB
            )
        }

    }

    fun Context.application(): MyApplication = applicationContext as MyApplication
}