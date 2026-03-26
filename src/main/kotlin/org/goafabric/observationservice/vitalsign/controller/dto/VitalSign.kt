package org.goafabric.observationservice.vitalsign.controller.dto

import java.time.LocalDateTime


data class VitalSign(
    val id: String,
    val version: Long,

    val patientId: String,

    val effectiveDateTime: LocalDateTime,

    val vitalSignDetails: List<VitalSignDetails> = mutableListOf()
)

data class VitalSignDetails (
    val id: String,
    val version: Long,
    val effectiveDateTime: LocalDateTime,
    val type: String,
    val code: String,
    val subject: String,
    val valueQuantity: String
)