package org.goafabric.observationservice.vitalsign.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Version
import java.time.LocalDateTime

@Table(name = "laboratory_data")
@Entity
class VitalSignDetailsEo (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String? = null,

    @Version //optimistic locking
    var version: Long?= null,

    var effectiveDateTime: LocalDateTime,

    var type: String,

    var code: String,

    var subject: String,

    var valueQuantity: String
)