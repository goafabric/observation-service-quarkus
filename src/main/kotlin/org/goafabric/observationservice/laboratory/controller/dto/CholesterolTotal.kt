package org.goafabric.observationservice.laboratory.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class CholesterolTotal(
    override val id: String? = UUID.randomUUID().toString(),
    override val code: Coding = Coding("http://loinc.org", "2098-6", "Cholesterol [Moles/Volume]"),
    override val subject: String,
    override val valueQuantity: ValueQuantityMol,
    override val effectiveDateTime: LocalDateTime
) : Observation