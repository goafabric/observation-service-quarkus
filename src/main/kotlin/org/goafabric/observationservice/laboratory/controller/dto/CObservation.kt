package org.goafabric.observationservice.laboratory.controller.dto

import java.time.LocalDateTime
import java.util.UUID


data class CObservation (
    val id: String? = UUID.randomUUID().toString(),
    val code: Coding?,
    val subject: String,
    val valueQuantity: ValueQuantity,
    val effectiveDateTime: LocalDateTime
)