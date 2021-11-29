package com.la.pampa.phone.catalog.domain.repository

import com.la.pampa.phone.catalog.core.ResultOf
import com.la.pampa.phone.catalog.domain.dto.PhoneDto
import com.la.pampa.phone.catalog.domain.models.Phone
import kotlinx.coroutines.flow.Flow

interface PhoneRepository {

    /**
     * Function that get phones of local
     *
     */
    fun getPhones(): Flow<List<Phone>>

    /**
     * Function that save phone on local
     *
     */
    suspend fun savePhone(phoneDto: PhoneDto): ResultOf<Boolean>

}