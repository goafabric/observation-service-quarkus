package org.goafabric.observationservice.vitalsign.controller.dto

import jakarta.persistence.Embeddable

@Embeddable
data class Coding (
    val system: String,
    val code: String,
    val display: String
)