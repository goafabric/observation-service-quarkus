package org.goafabric.observationservice.llm

import io.quarkiverse.mcp.server.Tool
import jakarta.enterprise.context.ApplicationScoped
import org.goafabric.observationservice.laboratory.controller.dto.LaboratoryHead
import org.goafabric.observationservice.laboratory.logic.LaboratoryDataLogic
import org.goafabric.observationservice.vitalsign.controller.dto.VitalSign
import org.goafabric.observationservice.vitalsign.logic.VitalSignLogic
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@ApplicationScoped
class ObservationTool(val vitalSignLogic: VitalSignLogic, val laboratoryDataLogic: LaboratoryDataLogic) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    @Tool
    fun getVitalSignsByPatientName(patientName: String): List<VitalSign> {
        log.info("retrieving vital signs for $patientName")
        val vitalSigns = vitalSignLogic.getByPatientId(getPatientId(patientName))
        if (vitalSigns.toMutableList().isEmpty()) error("no vital signs found for $patientName")
        return vitalSigns
    }

    @Tool()
    fun getLaboratoryDataByPatientName(patientName: String): List<LaboratoryHead> {
        log.info("retrieving laboratory data for $patientName")
        val labData = laboratoryDataLogic.getByPatientId(getPatientId(patientName))
        if (labData.toMutableList().isEmpty()) error("no laboratory found for $patientName")
        return labData
    }

    private fun getPatientId(patientName: String) : String {
        return when (patientName.lowercase()) {
            "homer" -> "1"
            "marge" -> "2"
            "bart" -> "3"
            "lisa" -> "4"
            else -> error("Unsupported PatientId")
        }
    }
}