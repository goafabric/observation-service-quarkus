package org.goafabric.observationservice.laboratory.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class Thyrotropin (
    override val id: String? = UUID.randomUUID().toString(),
    override val code: Coding = Coding(
        "http://loinc.org",
        "11579-0",
        "Thyrotropin (TSH) [Units/volume] in Serum or Plasma"
    ),
    override val subject: String,
    override val valueQuantity: ValueQuantityIU, // e.g., uIU/mL
    override val effectiveDateTime: LocalDateTime
) : Observation
