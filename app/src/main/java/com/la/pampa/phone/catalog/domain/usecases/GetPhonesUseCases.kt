package com.la.pampa.phone.catalog.domain.usecases

import com.la.pampa.phone.catalog.domain.models.Phone
import com.la.pampa.phone.catalog.domain.repository.PhoneRepository
import kotlinx.coroutines.flow.Flow

class GetPhonesUseCases(
    private val repository: PhoneRepository
) {
    operator fun invoke(): Flow<List<Phone>> = repository.getPhones()
}