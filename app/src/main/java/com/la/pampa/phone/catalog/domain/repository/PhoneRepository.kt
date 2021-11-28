package com.la.pampa.phone.catalog.domain.repository

import com.la.pampa.phone.catalog.core.ResultOf
import com.la.pampa.phone.catalog.domain.dto.PhoneDto
import com.la.pampa.phone.catalog.domain.models.Phone

interface PhoneRepository {

    /**
     * Function that get phones of local
     *
     */
    suspend fun getPhones(): ResultOf<List<Phone>>

    /**
     * Function that save phone on local
     *
     */
    suspend fun savePhone(phoneDto: PhoneDto): ResultOf<Boolean>

}