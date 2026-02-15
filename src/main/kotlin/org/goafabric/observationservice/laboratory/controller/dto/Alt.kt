package org.goafabric.observationservice.laboratory.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class Alt(
    override val id: String? = UUID.randomUUID().toString(),
    override val code: Coding = Coding(
        "http://loinc.org",
        "1742-6",
        "Alanine aminotransferase (ALT) [Enzymatic activity/volume] in Serum or Plasma"
    ),
    override val subject: String,
    override val valueQuantity: ValueQuantityEnzyme,
    override val effectiveDateTime: LocalDateTime
) : Observation