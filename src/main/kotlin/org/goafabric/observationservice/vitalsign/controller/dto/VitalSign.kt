package org.goafabric.observationservice.vitalsign.controller.dto

import java.time.LocalDateTime


data class VitalSign(
    val id: String,
    val version: Long,

    val patientId: String,

    val effectiveDateTime: LocalDateTime,

    val observations: List<Observation> = mutableListOf()
)