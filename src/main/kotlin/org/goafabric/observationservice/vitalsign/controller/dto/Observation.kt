package org.goafabric.observationservice.vitalsign.controller.dto

import java.time.LocalDateTime
import java.util.UUID


data class Observation (
    val id: String? = UUID.randomUUID().toString(),
    val code: Coding,
    val subject: String,
    val valueQuantity: ValueQuantity,
    val effectiveDateTime: LocalDateTime = LocalDateTime.now()
)