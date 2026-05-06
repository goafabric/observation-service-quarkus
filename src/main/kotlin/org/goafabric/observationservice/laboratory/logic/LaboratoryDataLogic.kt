package org.goafabric.observationservice.laboratory.logic

import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.goafabric.observationservice.laboratory.controller.dto.Observation
import org.goafabric.observationservice.laboratory.controller.dto.LaboratoryHead
import org.goafabric.observationservice.laboratory.logic.mapper.LaboratoryDataMapper
import org.goafabric.observationservice.laboratory.persistence.LaboratoryDataRepository
import org.goafabric.observationservice.laboratory.persistence.entity.LaboratoryDataDetailsEo
import org.goafabric.observationservice.laboratory.persistence.entity.LaboratoryHeadEo
import java.time.LocalDateTime

@ApplicationScoped
@Transactional
class LaboratoryDataLogic(
    val laboratoryDataRepository: LaboratoryDataRepository,
    val laboratoryDataMapper: LaboratoryDataMapper
    ) {

    fun getByPatientId(patientId: String): List<LaboratoryHead> {
        val laboratoryData = laboratoryDataRepository.findByPatientId(patientId)
        return laboratoryDataMapper.map(laboratoryData)
    }

    fun save(observations: List<Observation>, patiendId: String) {
        val labDetails = observations.map { obervation -> LaboratoryDataDetailsEo(
            effectiveDateTime = LocalDateTime.now(), code = obervation.code, subject = obervation.subject, valueQuantity = obervation.valueQuantity) }

        laboratoryDataRepository.save(
            LaboratoryHeadEo(patientId = patiendId, effectiveDateTime = LocalDateTime.now(), laboratoryDataDetails = labDetails.toMutableList())
        )
    }

}