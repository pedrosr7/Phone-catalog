package com.la.pampa.phone.catalog.domain.dto

data class PhoneDto(
    val name: String,
    val manufacturer: String,
    val description: String,
    val price: Int,
    val imageFileName: String,
    val screen: String,
    val processor: String,
    val ram: Int
)