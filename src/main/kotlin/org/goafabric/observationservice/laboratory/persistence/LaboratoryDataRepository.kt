package org.goafabric.observationservice.laboratory.persistence

import io.quarkus.hibernate.panache.PanacheRepository
import jakarta.data.repository.Find
import org.goafabric.observationservice.laboratory.persistence.entity.LaboratoryHeadEo

interface LaboratoryDataRepository : PanacheRepository.Managed<LaboratoryHeadEo, String> {
    @Find
    fun findByPatientId(patientId: String) : List<LaboratoryHeadEo>

    fun save(laboratoryHeadEo: LaboratoryHeadEo): LaboratoryHeadEo {
        persist(laboratoryHeadEo)
        return laboratoryHeadEo
    }
}