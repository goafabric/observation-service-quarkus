package org.goafabric.observationservice.vitalsign.persistence.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "vital_sign")
@Entity
class VitalSignEo (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @Version //optimistic locking
    var version: Long?= null,

    var patientId: String,

    var effectiveDateTime: LocalDateTime,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "vital_sign_id")
    var vitalSignDetails: List<VitalSignDetailsEo> = mutableListOf()
)