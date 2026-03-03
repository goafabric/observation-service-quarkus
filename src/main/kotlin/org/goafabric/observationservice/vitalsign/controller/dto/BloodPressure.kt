package org.goafabric.observationservice.vitalsign.controller.dto

import java.time.LocalDateTime
import java.util.*

data class BloodPressure(
    override val id: String? = UUID.randomUUID().toString(),
    override val code: Coding = Coding("http://loinc.org", "85354-6", "Blood Pressure"), //8480-6 → Systolic, 8480-4 → Diastoli, 8480-9 → General
    override val subject: String,
    override val effectiveDateTime: LocalDateTime = LocalDateTime.now(),
    override val valueQuantity: ValueQuantityMmHg,
    //bodysite + list of components (systolic, diastolic)
) : Observation