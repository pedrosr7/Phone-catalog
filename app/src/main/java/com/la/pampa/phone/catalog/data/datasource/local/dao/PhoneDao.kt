package com.la.pampa.phone.catalog.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.la.pampa.phone.catalog.data.datasource.local.model.PhoneEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PhoneDao {
    @Query("SELECT * FROM phone")
    fun getAll(): Flow<List<PhoneEntity>>

    @Insert
    suspend fun insertAll(vararg phone: PhoneEntity)

    @Delete
    suspend fun delete(phone: PhoneEntity)
}