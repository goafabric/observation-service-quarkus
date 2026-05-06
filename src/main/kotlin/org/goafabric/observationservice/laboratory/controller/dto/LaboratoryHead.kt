package org.goafabric.observationservice.laboratory.controller.dto

import java.time.LocalDateTime

class LaboratoryHead (
    val id: String,
    val version: Long,

    val patientId: String,

    val effectiveDateTime: LocalDateTime,
    val laboratoryDataDetails: MutableList<LaboratoryDataDetails> = mutableListOf()
)

class LaboratoryDataDetails (
    val id: String,
    val version: Long,
    val effectiveDateTime: LocalDateTime,
    val code: Coding,
    val subject: String,
    val valueQuantity: ValueQuantity
)