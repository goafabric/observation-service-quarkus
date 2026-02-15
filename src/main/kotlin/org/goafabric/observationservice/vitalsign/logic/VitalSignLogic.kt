package org.goafabric.observationservice.vitalsign.logic

import com.fasterxml.jackson.databind.json.JsonMapper
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.goafabric.observationservice.vitalsign.controller.dto.BloodPressure
import org.goafabric.observationservice.vitalsign.controller.dto.BodyHeight
import org.goafabric.observationservice.vitalsign.controller.dto.BodyWeight
import org.goafabric.observationservice.vitalsign.persistence.VitalSignRepository
import org.goafabric.observationservice.vitalsign.persistence.entity.VitalSignEo
import org.goafabric.observationservice.vitalsign.persistence.entity.VitalSignType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

@ApplicationScoped
@Transactional
class VitalSignLogic(
    val vitalSignRepository: VitalSignRepository,
    val jsonMapper: JsonMapper
) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    fun save(bloodPressure: BloodPressure) {
        vitalSignRepository.save(VitalSignEo(
            patientId = getPatientId(bloodPressure.subject),
            type = VitalSignType.BLOOD_PRESSURE,
            createdAt = LocalDateTime.now(),
            content = jsonMapper.writeValueAsString(bloodPressure))
        )
    }

    fun save(bloodPressures: List<BloodPressure>) {
        vitalSignRepository.save(VitalSignEo(
            patientId = getPatientId(bloodPressures.first().subject),
            type = VitalSignType.BLOOD_PRESSURE_SERIES,
            createdAt = LocalDateTime.now(),
            content = jsonMapper.writeValueAsString(bloodPressures))
        )
    }

    fun save(bodyHeight: BodyHeight) {
        vitalSignRepository.save(VitalSignEo(
            patientId = getPatientId(bodyHeight.subject),
            type = VitalSignType.BODY_HEIGHT,
            createdAt = LocalDateTime.now(),
            content = jsonMapper.writeValueAsString(bodyHeight))
        )
    }

    fun save(bodyWeight: BodyWeight) {
        vitalSignRepository.save(VitalSignEo(
            patientId = getPatientId(bodyWeight.subject),
            type = VitalSignType.BODY_WEIGHT,
            createdAt = LocalDateTime.now(),
            content = jsonMapper.writeValueAsString(bodyWeight))
        )
    }

    fun getObservations() : Iterable<VitalSignEo> {
        return vitalSignRepository.findAll().toList()
    }

    fun getByPatientId(patientId: String) : Iterable<VitalSignEo> {
        return vitalSignRepository.findByPatientId(patientId)
    }

    private fun getPatientId(subject: String): String {
        return subject.replace("Patient/", "")
    }


}