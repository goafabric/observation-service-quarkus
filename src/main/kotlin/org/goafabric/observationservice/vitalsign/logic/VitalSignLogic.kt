package org.goafabric.observationservice.vitalsign.logic

import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.goafabric.observationservice.vitalsign.controller.dto.*
import org.goafabric.observationservice.vitalsign.logic.mapper.VitalSignMapper
import org.goafabric.observationservice.vitalsign.persistence.VitalSignRepository
import org.goafabric.observationservice.vitalsign.persistence.entity.VitalSignHeadEo
import org.goafabric.observationservice.vitalsign.persistence.entity.VitalSignObservationEo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

@ApplicationScoped
@Transactional
class VitalSignLogic(
    val vitalSignRepository: VitalSignRepository,
    val vitalSignMapper: VitalSignMapper) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    fun save(observation: Observation) {
        save(listOf(observation))
    }

    fun save(observations: List<Observation>) {
        val patientId = getPatientId(observations.first().subject)
        val labDetails = observations.map { obervation ->
            VitalSignObservationEo(
                effectiveDateTime = LocalDateTime.now(),
                code = obervation.code,
                subject = obervation.subject,
                valueQuantity = obervation.valueQuantity
            )
        }

        vitalSignRepository.save(
            VitalSignHeadEo(patientId = patientId, effectiveDateTime = LocalDateTime.now(), observations = labDetails.toMutableList())
        )
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

}