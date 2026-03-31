package org.goafabric.observationservice.vitalsign.persistence

import io.quarkus.hibernate.panache.PanacheRepository
import jakarta.data.repository.Find
import jakarta.data.repository.Repository
import org.goafabric.observationservice.vitalsign.persistence.entity.VitalSignEo

interface VitalSignRepository : PanacheRepository.Managed<VitalSignEo, String> {

    @Find
    fun findByPatientId(patientId: String) : List<VitalSignEo>

    fun save(vitalSignEo: VitalSignEo): VitalSignEo {
        persist(vitalSignEo)
        return vitalSignEo
    }

}