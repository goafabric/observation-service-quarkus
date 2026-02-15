package org.goafabric.observationservice.vitalsign.controller.dto

data class Coding (
    val system: String,
    val code: String,
    val display: String
)