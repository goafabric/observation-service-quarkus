package org.goafabric.observationservice.laboratory.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class Ast(
    override val id: String? = UUID.randomUUID().toString(),
    override val code: Coding = Coding(
        "http://loinc.org",
        "1920-8",
        "Aspartate aminotransferase (AST) [Enzymatic activity/volume] in Serum or Plasma"
    ),
    override val subject: String,
    override val valueQuantity: ValueQuantityEnzyme, // e.g., U/L
    override val effectiveDateTime: LocalDateTime
) : Observation
