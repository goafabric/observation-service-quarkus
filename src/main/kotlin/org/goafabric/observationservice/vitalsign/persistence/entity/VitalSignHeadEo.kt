package org.goafabric.observationservice.vitalsign.persistence.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "vital_sign_head")
@Entity
class VitalSignHeadEo (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String? = null,

    @Version //optimistic locking
    var version: Long?= null,

    var patientId: String,

    var effectiveDateTime: LocalDateTime,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "vital_sign_id")
    var observations: MutableList<VitalSignObservationEo> = mutableListOf()
)