package org.goafabric.observationservice.vitalsign.logic

import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.goafabric.observationservice.vitalsign.controller.dto.BloodPressure
import org.goafabric.observationservice.vitalsign.controller.dto.BodyHeight
import org.goafabric.observationservice.vitalsign.controller.dto.BodyWeight
import org.goafabric.observationservice.vitalsign.controller.dto.Observation
import org.goafabric.observationservice.vitalsign.persistence.VitalSignRepository
import org.goafabric.observationservice.vitalsign.persistence.entity.VitalSignDetailsEo
import org.goafabric.observationservice.vitalsign.persistence.entity.VitalSignEo
import org.goafabric.observationservice.vitalsign.persistence.entity.VitalSignType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

@ApplicationScoped
@Transactional
class VitalSignLogic(
    val vitalSignRepository: VitalSignRepository) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    fun save(bloodPressure: BloodPressure) {
        vitalSignRepository.save(VitalSignEo(
            patientId = getPatientId(bloodPressure.subject),
            effectiveDateTime = LocalDateTime.now(),
            vitalSignDetails = listOf(map(VitalSignType.BLOOD_PRESSURE, bloodPressure))
        ))
    }

    fun save(bloodPressures: List<BloodPressure>) {
        vitalSignRepository.save(VitalSignEo(
            patientId = getPatientId(bloodPressures.first().subject),
            effectiveDateTime = LocalDateTime.now(),
            vitalSignDetails = bloodPressures.map { bloodPressure -> map(VitalSignType.BLOOD_PRESSURE_SERIES, bloodPressure) }
        ))
    }

    fun save(bodyHeight: BodyHeight) {
        vitalSignRepository.save(VitalSignEo(
            patientId = getPatientId(bodyHeight.subject),
            effectiveDateTime = LocalDateTime.now(),
            vitalSignDetails = listOf(map(VitalSignType.BODY_HEIGHT, bodyHeight))
        ))
    }

    fun save(bodyWeight: BodyWeight) {
        vitalSignRepository.save(VitalSignEo(
            patientId = getPatientId(bodyWeight.subject),
            effectiveDateTime = LocalDateTime.now(),
            vitalSignDetails = listOf(map(VitalSignType.BODY_WEIGHT, bodyWeight))
        ))
    }

    fun getObservations() : List<VitalSignEo> {
        return vitalSignRepository.findAll().list() //.toList()
    }

    //uses workaround for lazy without mapping
    fun getByPatientId(patientId: String) : List<VitalSignEo> {
        val vitalSigns = vitalSignRepository.findByPatientId(patientId)
        vitalSigns.forEach { vitalSignEo -> vitalSignEo.vitalSignDetails.size }
        return vitalSigns;
    }

    private fun getPatientId(subject: String): String {
        return subject.replace("Patient/", "")
    }

    private fun map(type: VitalSignType, observation: Observation) : VitalSignDetailsEo {
        return VitalSignDetailsEo(type = type.toString(), effectiveDateTime = LocalDateTime.now(),
            code = observation.code.toString(), subject = observation.subject, valueQuantity = observation.valueQuantity.toString())
    }
}