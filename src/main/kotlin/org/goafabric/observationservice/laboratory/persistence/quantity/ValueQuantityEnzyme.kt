package org.goafabric.observationservice.laboratory.persistence.quantity

import org.goafabric.observationservice.laboratory.controller.dto.ValueQuantity

data class ValueQuantityEnzyme(
    override val code: String = "U/L",
    override val unit: String = "U/L",
    override val value: Double,
    override val system: String = "http://unitsofmeasure.org"
) : ValueQuantity(code, unit, value, system)
