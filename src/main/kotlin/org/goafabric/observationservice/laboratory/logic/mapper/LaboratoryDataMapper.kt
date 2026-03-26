package org.goafabric.observationservice.laboratory.logic.mapper

import org.goafabric.observationservice.laboratory.controller.dto.LaboratoryData
import org.goafabric.observationservice.laboratory.persistence.entity.LaboratoryDataEo
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface LaboratoryDataMapper {
    fun map(value: List<LaboratoryDataEo>): List<LaboratoryData>
}