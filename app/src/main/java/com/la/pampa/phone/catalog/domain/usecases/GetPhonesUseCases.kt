package com.la.pampa.phone.catalog.domain.usecases

import com.la.pampa.phone.catalog.core.ResultOf
import com.la.pampa.phone.catalog.core.plataform.BaseUseCase
import com.la.pampa.phone.catalog.domain.models.Phone
import com.la.pampa.phone.catalog.domain.repository.PhoneRepository

class GetPhonesUseCases(
    private val repository: PhoneRepository
): BaseUseCase<List<Phone>>() {
    override suspend fun run(): ResultOf<List<Phone>> =
        repository.getPhones()
}