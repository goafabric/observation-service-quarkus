package org.goafabric.observationservice.vitalsign.controller.dto

data class ValueQuantityKg (
    override val code: String = "kg",
    override val unit: String = "kg",
    override val value: Int,
    override val system: String = "http://unitsofmeasure.org"
) : ValueQuantity