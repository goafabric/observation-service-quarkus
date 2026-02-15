package org.goafabric.observationservice.laboratory.persistence

import jakarta.data.repository.CrudRepository
import jakarta.data.repository.Find
import jakarta.data.repository.Repository
import org.goafabric.observationservice.laboratory.persistence.entity.LaboratoryDataEo

@Repository
interface LaboratoryDataRepository : CrudRepository<LaboratoryDataEo, String> {
    @Find
    fun findByPatientId(patientId: String) : List<LaboratoryDataEo>
}