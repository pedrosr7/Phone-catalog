package com.la.pampa.phone.catalog.data.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone")
data class PhoneEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "manufacturer")
    val manufacturer: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "imageFileName")
    val imageFileName: String,
    @ColumnInfo(name = "screen")
    val screen: String,
    @ColumnInfo(name = "processor")
    val processor: String,
    @ColumnInfo(name = "ram")
    val ram: Int,
)