package com.la.pampa.phone.catalog.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.la.pampa.phone.catalog.data.datasource.local.model.PhoneEntity

@Dao
interface PhoneDao {
    @Query("SELECT * FROM phone")
    fun getAll(): List<PhoneEntity>

    @Insert
    fun insertAll(vararg phone: PhoneEntity)

    @Delete
    fun delete(phone: PhoneEntity)
}