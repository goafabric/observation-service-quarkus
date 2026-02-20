package org.goafabric.observationservice.laboratory.persistence

import io.quarkus.hibernate.panache.PanacheRepository
import jakarta.data.repository.Find
import jakarta.data.repository.Repository
import org.goafabric.observationservice.laboratory.persistence.entity.LaboratoryDataEo

@Repository
interface LaboratoryDataRepository : PanacheRepository.Managed<LaboratoryDataEo, String> {
    @Find
    fun findByPatientId(patientId: String) : List<LaboratoryDataEo>

    fun save(laboratoryDataEo: LaboratoryDataEo): LaboratoryDataEo {
        persist(laboratoryDataEo)
        return laboratoryDataEo
    }
}