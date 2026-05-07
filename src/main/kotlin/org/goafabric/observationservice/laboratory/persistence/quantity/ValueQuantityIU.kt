package org.goafabric.observationservice.laboratory.persistence.quantity

import org.goafabric.observationservice.laboratory.controller.dto.ValueQuantity

data class ValueQuantityIU(
    override val code: String = "uIU/mL",
    override val unit: String = "uIU/mL",
    override val value: Double,
    override val system: String = "http://unitsofmeasure.org"
) : ValueQuantity(code, unit, value, system)