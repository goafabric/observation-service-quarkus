package org.goafabric.observationservice.vitalsign.controller.dto

data class ValueQuantityCm (
    val code: String = "cm",
    val unit: String = "cm",
    val value: Int,
    val system: String = "http://unitsofmeasure.org"
)