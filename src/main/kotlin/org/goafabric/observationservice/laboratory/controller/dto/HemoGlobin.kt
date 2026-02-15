package org.goafabric.observationservice.laboratory.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class HemoGlobin(
    override val id: String? = UUID.randomUUID().toString(),
    override val code: Coding = Coding("http://loinc.org", "4548-4", "Hemoglobin [Moles/Volume]"),
    override val subject: String,
    override val valueQuantity: ValueQuantityPercent,
    override val effectiveDateTime: LocalDateTime
) : Observation