package org.goafabric.observationservice.laboratory.persistence.entity

import jakarta.persistence.*
import org.goafabric.observationservice.laboratory.controller.dto.Coding
import java.time.LocalDateTime

@Table(name = "laboratory_data_details")
@Entity
class LaboratoryDataDetailsEo (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String? = null,

    @Version //optimistic locking
    var version: Long?= null,

    var effectiveDateTime: LocalDateTime,

    @Embedded
    var code: Coding,

    var subject: String,

    var valueQuantity: String
)