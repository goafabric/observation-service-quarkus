package org.goafabric.observationservice.laboratory.logic

import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.goafabric.observationservice.laboratory.controller.dto.Observation
import org.goafabric.observationservice.laboratory.persistence.LaboratoryDataRepository
import org.goafabric.observationservice.laboratory.persistence.entity.LaboratoryDataDetailsEo
import org.goafabric.observationservice.laboratory.persistence.entity.LaboratoryDataEo
import java.time.LocalDateTime

@ApplicationScoped
@Transactional
class LaboratoryDataLogic(
    val laboratoryDataRepository: LaboratoryDataRepository,
    ) {

    fun getByPatientId(patientId: String): List<LaboratoryDataEo> {
        val laboratoryData = laboratoryDataRepository.findByPatientId(patientId)
        laboratoryData.forEach { eo ->  eo.laboratoryDataDetailsEo.size }
        return laboratoryData
    }

    fun save(observations: List<Observation>, patiendId: String) {
        val labDetails = observations.map { obervation -> LaboratoryDataDetailsEo(
            effectiveDateTime = LocalDateTime.now(), code = obervation.code.toString(), subject = obervation.subject, valueQuantity = obervation.valueQuantity.toString() ) }

        laboratoryDataRepository.save(
            LaboratoryDataEo(patientId = patiendId, effectiveDateTime = LocalDateTime.now(), laboratoryDataDetailsEo = labDetails)
        )
    }

}