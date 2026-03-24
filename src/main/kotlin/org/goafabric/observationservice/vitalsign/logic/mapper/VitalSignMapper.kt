package org.goafabric.observationservice.vitalsign.logic.mapper

import org.goafabric.observationservice.vitalsign.controller.dto.Observation
import org.goafabric.observationservice.vitalsign.controller.dto.VitalSign
import org.goafabric.observationservice.vitalsign.persistence.entity.VitalSignDetailsEo
import org.goafabric.observationservice.vitalsign.persistence.entity.VitalSignEo
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface VitalSignMapper {
    fun map(value: VitalSignDetailsEo): VitalSign
    fun map(value: VitalSign): VitalSignEo
    fun map(value: List<VitalSignDetailsEo>): List<VitalSign>
    //fun map(value: List<VitalSign>): List<VitalSignEo>
}