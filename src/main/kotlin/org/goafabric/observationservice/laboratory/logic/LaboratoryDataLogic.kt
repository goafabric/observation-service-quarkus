package org.goafabric.observationservice.laboratory.logic

import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.goafabric.observationservice.laboratory.controller.dto.Observation
import org.goafabric.observationservice.laboratory.controller.dto.LaboratoryHead
import org.goafabric.observationservice.laboratory.logic.mapper.LaboratoryDataMapper
import org.goafabric.observationservice.laboratory.persistence.LaboratoryHeadRepository
import org.goafabric.observationservice.laboratory.persistence.entity.LaboratoryObservationEo
import org.goafabric.observationservice.laboratory.persistence.entity.LaboratoryHeadEo
import java.time.LocalDateTime

@ApplicationScoped
@Transactional
class LaboratoryDataLogic(
    val laboratoryHeadRepository: LaboratoryHeadRepository,
    val laboratoryDataMapper: LaboratoryDataMapper
    ) {

    fun getByPatientId(patientId: String): List<LaboratoryHead> {
        val laboratoryData = laboratoryHeadRepository.findByPatientId(patientId)
        return laboratoryDataMapper.map(laboratoryData)
    }

    fun save(observations: List<Observation>, patiendId: String) {
        val labDetails = observations.map { obervation -> LaboratoryObservationEo(
            effectiveDateTime = LocalDateTime.now(), code = obervation.code, subject = obervation.subject, valueQuantity = obervation.valueQuantity) }

        laboratoryHeadRepository.save(
            LaboratoryHeadEo(patientId = patiendId, effectiveDateTime = LocalDateTime.now(), observations = labDetails.toMutableList())
        )
    }

}