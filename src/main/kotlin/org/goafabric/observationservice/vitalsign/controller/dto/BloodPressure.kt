package org.goafabric.observationservice.vitalsign.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class BloodPressure(
    override val id: String? = UUID.randomUUID().toString(),
    override val code: Coding = Coding("http://loinc.org", "85354-9", "Blood Pressure"),
    override val subject: String,
    override val effectiveDateTime: LocalDateTime = LocalDateTime.now(),
    override val valueQuantity: ValueQuantity? = null,
    //bodysite + list of components
    val systolic: Int,
    val diastolic: Int,
) : Observation