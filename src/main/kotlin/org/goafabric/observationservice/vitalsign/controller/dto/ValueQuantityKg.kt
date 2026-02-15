package org.goafabric.observationservice.vitalsign.controller.dto

data class ValueQuantityKg (
    val code: String = "kg",
    val unit: String = "kg",
    val value: Int,
    val system: String = "http://unitsofmeasure.org"
)