package org.goafabric.observationservice.laboratory.persistence.entity

import jakarta.persistence.*
import org.goafabric.observationservice.laboratory.controller.dto.Coding
import org.goafabric.observationservice.laboratory.controller.dto.ValueQuantity
import org.hibernate.annotations.EmbeddedColumnNaming
import java.time.LocalDateTime

@Table(name = "laboratory_observations")
@Entity
class LaboratoryObservationEo (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String? = null,

    @Version //optimistic locking
    var version: Long?= null,

    var effectiveDateTime: LocalDateTime,

    @Embedded
    @EmbeddedColumnNaming("code_%s")
    var code: Coding,

    var subject: String,

    @Embedded
    @EmbeddedColumnNaming("value_%s")
    var valueQuantity: ValueQuantity
)