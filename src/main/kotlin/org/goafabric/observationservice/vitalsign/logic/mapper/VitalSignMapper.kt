package org.goafabric.observationservice.vitalsign.logic.mapper

import org.goafabric.observationservice.vitalsign.controller.dto.VitalSign
import org.goafabric.observationservice.vitalsign.persistence.entity.VitalSignEo
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface VitalSignMapper {
    fun map(value: List<VitalSignEo>): List<VitalSign>
}