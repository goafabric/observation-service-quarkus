package org.goafabric.observationservice.vitalsign.persistence.entity

import com.fasterxml.jackson.annotation.JsonRawValue
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Version
import java.time.LocalDateTime

@Table(name = "vital_sign")
@Entity
class VitalSignEo (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @Version //optimistic locking
    val version: Long? = null,

    val patientId: String,

    val type: VitalSignType,
    val createdAt: LocalDateTime,

    @Column(length = 4096)
    @JsonRawValue
    val content: String
)