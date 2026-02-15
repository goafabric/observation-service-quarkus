package org.goafabric.observationservice.vitalsign.persistence

import jakarta.data.repository.CrudRepository
import jakarta.data.repository.Find
import jakarta.data.repository.Repository
import org.goafabric.observationservice.vitalsign.persistence.entity.VitalSignEo

@Repository
interface VitalSignRepository : CrudRepository<VitalSignEo, String> {

    @Find
    fun findByPatientId(patientId: String) : List<VitalSignEo>

}