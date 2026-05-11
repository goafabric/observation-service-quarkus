package org.goafabric.observationservice.vitalsign.persistence.entity

import jakarta.persistence.*
import org.goafabric.observationservice.vitalsign.controller.dto.Coding
import org.goafabric.observationservice.vitalsign.controller.dto.ValueQuantity
import org.hibernate.annotations.EmbeddedColumnNaming
import java.time.LocalDateTime

@Table(name = "laboratory_observations")
@Entity
class VitalSignObservationEo (
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