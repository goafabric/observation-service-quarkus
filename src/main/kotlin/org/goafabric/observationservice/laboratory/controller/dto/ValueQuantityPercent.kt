package org.goafabric.observationservice.laboratory.controller.dto

import java.time.LocalDateTime

data class ValueQuantityPercent (
    override val code: String = "%",
    override val unit: String = "%",
    override val value: Double,
    override val system: String = "http://unitsofmeasure.org"
) : ValueQuantity