package com.la.pampa.phone.catalog.di

import com.la.pampa.phone.catalog.data.datasource.local.dao.PhoneDao
import com.la.pampa.phone.catalog.data.repository.PhoneRepositoryImpl
import com.la.pampa.phone.catalog.domain.repository.PhoneRepository
import org.koin.dsl.module

val repositoryModule = module {

    fun providePhoneRepository(dao : PhoneDao): PhoneRepository {
        return PhoneRepositoryImpl(dao)
    }
    single { providePhoneRepository(get()) }
    //single<PhoneRepository> { PhoneRepositoryImpl(get()) }
}