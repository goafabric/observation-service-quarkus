package org.goafabric.observationservice.vitalsign.persistence

import io.quarkus.hibernate.panache.PanacheRepository
import jakarta.data.repository.Find
import org.goafabric.observationservice.vitalsign.persistence.entity.VitalSignHeadEo

interface VitalSignRepository : PanacheRepository.Managed<VitalSignHeadEo, String> {

    @Find
    fun findByPatientId(patientId: String) : List<VitalSignHeadEo>

    fun save(vitalSignHead: VitalSignHeadEo): VitalSignHeadEo {
        persist(vitalSignHead)
        return vitalSignHead
    }

}