package org.goafabric.observationservice.laboratory.persistence.quantity

import org.goafabric.observationservice.laboratory.controller.dto.ValueQuantity

data class ValueQuantityPercent (
    override val code: String = "%",
    override val unit: String = "%",
    override val value: Double,
    override val system: String = "http://unitsofmeasure.org"
) : ValueQuantity(code, unit, value, system)