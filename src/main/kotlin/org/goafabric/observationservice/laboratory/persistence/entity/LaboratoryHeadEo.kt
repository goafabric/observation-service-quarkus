package org.goafabric.observationservice.laboratory.persistence.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "laboratory_head")
@Entity
class LaboratoryHeadEo (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String? = null,

    @Version //optimistic locking
    var version: Long?= null,

    var patientId: String,

    var effectiveDateTime: LocalDateTime,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "lab_id")
    var observations: MutableList<LaboratoryObservationEo> = mutableListOf()
)