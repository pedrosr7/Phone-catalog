package com.la.pampa.phone.catalog.domain.usecases

import com.la.pampa.phone.catalog.core.ResultOf
import com.la.pampa.phone.catalog.core.plataform.BaseParamsUseCase
import com.la.pampa.phone.catalog.domain.dto.PhoneDto
import com.la.pampa.phone.catalog.domain.repository.PhoneRepository

class SavePhoneUseCases(
    private val repository: PhoneRepository
): BaseParamsUseCase<Boolean, PhoneDto>() {
    override suspend fun run(params: PhoneDto): ResultOf<Boolean> =
        repository.savePhone(params)
}