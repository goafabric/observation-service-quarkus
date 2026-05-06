package org.goafabric.observationservice.laboratory.controller.dto

import jakarta.persistence.Embeddable

@Embeddable
open class ValueQuantity (
    open val code: String,
    open val unit: String,
    open val value: Number,
    open val system: String,
)