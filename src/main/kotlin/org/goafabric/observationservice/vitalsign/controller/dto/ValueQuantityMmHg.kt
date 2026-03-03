package org.goafabric.observationservice.vitalsign.controller.dto


data class ValueQuantityMmHg(
    override val code: String = "mm[Hg]",
    override val unit: String = "mmHg",
    override val value: Int,
    override val system: String = "http://unitsofmeasure.org"
) : ValueQuantity