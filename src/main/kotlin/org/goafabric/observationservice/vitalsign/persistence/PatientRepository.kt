package org.goafabric.observationservice.vitalsign.persistence

import jakarta.enterprise.context.ApplicationScoped
import org.goafabric.observationservice.vitalsign.controller.dto.Patient
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@ApplicationScoped
class PatientRepository {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    private val patients: MutableList<Patient> = mutableListOf()

    fun save(patient: Patient): Patient {
        patients.add(patient)
        log.info(patient.toString())
        return patient;
    }

}