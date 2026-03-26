package org.goafabric.observationservice.laboratory.controller.dto

import java.time.LocalDateTime

class LaboratoryData (
    val id: String,
    var version: Long,

    var patientId: String,

    var effectiveDateTime: LocalDateTime,
    var laboratoryDataDetails: List<LaboratoryDataDetails> = mutableListOf()
)

class LaboratoryDataDetails (
    val id: String,
    val version: Long,
    val effectiveDateTime: LocalDateTime,
    val code: String,
    val subject: String,
    val valueQuantity: String
)