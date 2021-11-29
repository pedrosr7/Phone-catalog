package com.la.pampa.phone.catalog.domain.models

data class Phone(
    val id: Int,
    val name: String,
    val manufacturer: String,
    val description: String,
    val price: Int,
    val imageFileName: String,
    val screen: String,
    val processor: String,
    val ram: Int
) : Comparable<Phone> {
    override fun compareTo(other: Phone): Int = id.compareTo(other.id)
}