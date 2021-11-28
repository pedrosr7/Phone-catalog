package com.la.pampa.phone.catalog.core.extensions

import java.time.LocalDateTime
import java.time.ZoneId

fun LocalDateTime.toMillis(zone: ZoneId = ZoneId.systemDefault()) = atZone(zone)?.toInstant()?.toEpochMilli()
