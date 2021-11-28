package com.la.pampa.phone.catalog.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.la.pampa.phone.catalog.data.datasource.local.dao.PhoneDao
import com.la.pampa.phone.catalog.data.datasource.local.model.PhoneEntity

@Database(entities = [PhoneEntity::class], version = 1, exportSchema = false)
abstract class PhoneDatabase : RoomDatabase() {
    abstract fun phoneDao(): PhoneDao
}