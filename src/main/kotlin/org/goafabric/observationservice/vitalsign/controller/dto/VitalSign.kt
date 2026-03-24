package org.goafabric.observationservice.vitalsign.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class VitalSign(
    override val id: String?,
    override val code: Coding,
    override val subject: String,
    override val effectiveDateTime: LocalDateTime,
    override val valueQuantity: ValueQuantity,
) : Observation