package org.goafabric.observationservice.laboratory.persistence.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "laboratory_data")
@Entity
class LaboratoryDataEo (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String? = null,

    @Version //optimistic locking
    var version: Long?= null,

    var patientId: String,

    var effectiveDateTime: LocalDateTime,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "lab_id")
    var laboratoryDataDetails: MutableList<LaboratoryDataDetailsEo> = mutableListOf()
)