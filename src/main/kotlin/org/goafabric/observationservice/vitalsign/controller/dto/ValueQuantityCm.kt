package org.goafabric.observationservice.vitalsign.controller.dto

data class ValueQuantityCm (
    override val code: String = "cm",
    override val unit: String = "cm",
    override val value: Int,
    override val system: String = "http://unitsofmeasure.org"
) : ValueQuantity