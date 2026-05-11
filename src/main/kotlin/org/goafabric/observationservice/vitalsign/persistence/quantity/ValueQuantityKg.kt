package org.goafabric.observationservice.vitalsign.persistence.quantity

import org.goafabric.observationservice.vitalsign.controller.dto.ValueQuantity

data class ValueQuantityKg (
    override val code: String = "kg",
    override val unit: String = "kg",
    override val value: Int,
    override val system: String = "http://unitsofmeasure.org"
) : ValueQuantity(code, unit, value, system)