package org.goafabric.observationservice.vitalsign.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class BodyWeight(
    override val id: String? = UUID.randomUUID().toString(),
    override val code: Coding = Coding("http://loinc.org", "29463-7", "Body Weight"),
    override val subject: String,
    override val effectiveDateTime: LocalDateTime = LocalDateTime.now(),
    override val valueQuantity: ValueQuantityKg,
) : Observation