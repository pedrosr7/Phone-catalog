package com.la.pampa.phone.catalog.data.repository

import com.la.pampa.phone.catalog.core.ResultOf
import com.la.pampa.phone.catalog.data.datasource.local.dao.PhoneDao
import com.la.pampa.phone.catalog.data.datasource.mapper.toDomain
import com.la.pampa.phone.catalog.domain.dto.PhoneDto
import com.la.pampa.phone.catalog.domain.models.Phone
import com.la.pampa.phone.catalog.domain.repository.PhoneRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhoneRepositoryImpl(
    private val phoneDao: PhoneDao
): PhoneRepository {

    override suspend fun getPhones(): ResultOf<List<Phone>> {
        return try {
            val phone = withContext(Dispatchers.IO) {
              phoneDao.getAll().map { it.toDomain() }
            }
            withContext(Dispatchers.Main) {
                ResultOf.Success(phone)
            }

        } catch (e: Exception) {
            ResultOf.Failure(throwable = e)
        }
    }

    override suspend fun savePhone(phoneDto: PhoneDto): ResultOf<Boolean> {
        return try {
            withContext(Dispatchers.IO) {
                phoneDao.insertAll(phoneDto.toDomain())
            }

            ResultOf.Success(true)
        } catch (e: Exception) {
            ResultOf.Failure(throwable = e)
        }
    }

}