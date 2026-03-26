package org.goafabric.observationservice.vitalsign.logic

import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.goafabric.observationservice.vitalsign.controller.dto.*
import org.goafabric.observationservice.vitalsign.logic.mapper.VitalSignMapper
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
    val vitalSignRepository: VitalSignRepository,
    val vitalSignMapper: VitalSignMapper) {
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

    fun getObservations() : List<VitalSign> {
        return vitalSignMapper.map(vitalSignRepository.findAll().list())
    }

    //uses workaround for lazy without mapping
    fun getByPatientId(patientId: String) : List<VitalSign> {
        val vitalSigns = vitalSignRepository.findByPatientId(patientId)
        return vitalSignMapper.map(vitalSigns)
    }

    private fun getPatientId(subject: String): String {
        return subject.replace("Patient/", "")
    }

    private fun map(type: VitalSignType, observation: Observation) : VitalSignDetailsEo {
        return VitalSignDetailsEo(type = type.toString(), effectiveDateTime = LocalDateTime.now(),
            code = observation.code.toString(), subject = observation.subject, valueQuantity = observation.valueQuantity.toString())
    }
}