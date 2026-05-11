package org.goafabric.observationservice.vitalsign.persistence.quantity

import org.goafabric.observationservice.vitalsign.controller.dto.ValueQuantity


data class ValueQuantityMmHg(
    override val code: String = "mm[Hg]",
    override val unit: String = "mmHg",
    override val value: Int,
    override val system: String = "http://unitsofmeasure.org"
) : ValueQuantity(code, unit, value, system)