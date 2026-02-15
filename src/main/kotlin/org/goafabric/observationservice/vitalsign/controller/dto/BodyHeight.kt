package org.goafabric.observationservice.vitalsign.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class BodyHeight(
    override val id: String? = UUID.randomUUID().toString(),
    override val code: Coding = Coding("http://loinc.org", "8302-2", "Body Height"),
    override val subject: String,
    val valueQuantity: ValueQuantityCm,
    override val effectiveDateTime: LocalDateTime = LocalDateTime.now()
) : Observation