package com.la.pampa.phone.catalog.di

import android.app.Application
import androidx.room.Room
import com.la.pampa.phone.catalog.data.datasource.local.PhoneDatabase
import com.la.pampa.phone.catalog.data.datasource.local.dao.PhoneDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val phoneDB = module {
    fun provideDataBase(application: Application): PhoneDatabase {
        return Room.databaseBuilder(application, PhoneDatabase::class.java, "PHONEDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(dataBase: PhoneDatabase): PhoneDao {
        return dataBase.phoneDao()
    }
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }

}