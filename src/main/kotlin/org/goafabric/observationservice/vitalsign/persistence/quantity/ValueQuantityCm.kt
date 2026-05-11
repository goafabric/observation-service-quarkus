package org.goafabric.observationservice.vitalsign.persistence.quantity

import org.goafabric.observationservice.vitalsign.controller.dto.ValueQuantity

data class ValueQuantityCm (
    override val code: String = "cm",
    override val unit: String = "cm",
    override val value: Int,
    override val system: String = "http://unitsofmeasure.org"
) : ValueQuantity(code, unit, value, system)