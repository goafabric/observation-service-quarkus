package org.goafabric.observationservice.laboratory.controller.dto

data class ValueQuantityMol (
    override val code: String = "mmol/l",
    override val unit: String = "mmol/l",
    override val value: Double,
    override val system: String = "http://unitsofmeasure.org"
) : ValueQuantity(code, unit, value, system)