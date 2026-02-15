package org.goafabric.observationservice.laboratory.controller.dto

import java.time.LocalDateTime


interface Observation {
    val id: String?
    val code: Coding
    val subject: String
    val valueQuantity: ValueQuantity
    val effectiveDateTime: LocalDateTime
}