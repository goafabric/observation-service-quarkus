package org.goafabric.observationservice.laboratory.controller.dto

data class ValueQuantityEnzyme(
    override val code: String = "U/L",
    override val unit: String = "U/L",
    override val value: Double,
    override val system: String = "http://unitsofmeasure.org"
) : ValueQuantity(code, unit, value, system)
