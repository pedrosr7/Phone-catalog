package com.la.pampa.phone.catalog.data.datasource.mapper

import com.la.pampa.phone.catalog.data.datasource.local.model.PhoneEntity
import com.la.pampa.phone.catalog.domain.dto.PhoneDto
import com.la.pampa.phone.catalog.domain.models.Phone

fun PhoneEntity.toDomain() = Phone(
    id = id,
    name = name,
    description = description,
    manufacturer = manufacturer,
    price = price,
    imageFileName = imageFileName,
    imageFile = imageFile,
    screen = screen,
    processor = processor,
    ram = ram
)


fun PhoneDto.toDomain() = PhoneEntity(
    name = name,
    description = description,
    manufacturer = manufacturer,
    price = price,
    imageFileName = imageFileName,
    imageFile = imageFile,
    screen = screen,
    processor = processor,
    ram = ram
)