package org.goafabric.observationservice.vitalsign.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class BloodPressure(
    val id: String? = UUID.randomUUID().toString(),
    val code: Coding = Coding("http://loinc.org", "85354-9", "Blood Pressure"),
    val subject: String,
    //bodysite + list of components
    val systolic: Int,
    val diastolic: Int,
    val effectiveDateTime: LocalDateTime = LocalDateTime.now()
)