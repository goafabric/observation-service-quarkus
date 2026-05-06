package org.goafabric.observationservice.laboratory.controller.dto

import java.time.LocalDateTime

class LaboratoryHead (
    val id: String,
    val version: Long,

    val patientId: String,

    val effectiveDateTime: LocalDateTime,
    val laboratoryDataDetails: MutableList<Observation> = mutableListOf()
)
