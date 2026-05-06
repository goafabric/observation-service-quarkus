package org.goafabric.observationservice.laboratory.logic.mapper

import org.goafabric.observationservice.laboratory.controller.dto.LaboratoryHead
import org.goafabric.observationservice.laboratory.persistence.entity.LaboratoryHeadEo
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface LaboratoryDataMapper {
    fun map(value: List<LaboratoryHeadEo>): List<LaboratoryHead>
}