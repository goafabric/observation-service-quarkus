package org.goafabric.observationservice.vitalsign.controller.dto

import java.time.LocalDateTime


interface Observation {
    val id: String?
    val code: Coding
    val subject: String
    val effectiveDateTime: LocalDateTime
}