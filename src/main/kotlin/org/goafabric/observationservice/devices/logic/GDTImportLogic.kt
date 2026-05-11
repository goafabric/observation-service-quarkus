package org.goafabric.observationservice.devices.logic

import jakarta.enterprise.context.ApplicationScoped
import org.goafabric.observationservice.vitalsign.controller.dto.Coding
import org.goafabric.observationservice.vitalsign.controller.dto.Observation
import org.goafabric.observationservice.vitalsign.persistence.quantity.ValueQuantityMmHg
import org.goafabric.observationservice.vitalsign.logic.VitalSignLogic
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@ApplicationScoped
class GDTImportLogic(val vitalSignLogic: VitalSignLogic) {
    fun import(fileName: String): List<Observation> {
        val file = readFile(fileName)
        if (file.containsAll(listOf("840200SYS", "840400DIA"))) {
            val bloodPressures = parseBloodPressure(readFile(fileName))
            vitalSignLogic.save(bloodPressures)
            return bloodPressures
        } else {
            error("Unknown GDT File")
        }
    }

    fun parseBloodPressure(gdtLines: List<String>): List<Observation> {
        val observations = mutableListOf<Observation>()
        var patientId = ""
        var patientName = ""

        var currentDate = ""
        var currentTime = ""
        var systolic = 0
        var diastolic = 0
        var pulse = 0

        for (line in gdtLines) {
            if (line.length >= 6) {
                val fieldId = line.take(6)
                val value = line.substring(6)

                when (fieldId) {
                    "831000" -> patientId = value
                    "831100" -> patientName = value
                    "732100" -> currentDate = value
                    "732200" -> currentTime = value
                    "840300" -> systolic = value.toIntOrNull() ?: 0
                    "840500" -> diastolic = value.toIntOrNull() ?: 0
                    "840700" -> { // At this point, we have a full measurement, add to list
                        pulse = value.toIntOrNull() ?: 0
                        observations.add(
                            Observation(
                                subject = "Patient/${patientId}",
                                valueQuantity = ValueQuantityMmHg(value = systolic),
                                code = Coding("http://loinc.org", "85354-6", "Blood Pressure"),
                                //todo: diastolic
                                effectiveDateTime = LocalDateTime.parse(
                                    "$currentDate $currentTime",
                                    DateTimeFormatter.ofPattern("yyyyMMdd HHmm[ss]")
                                )
                            )
                        )
                    }
                }
            }
        }

        return observations
    }

    private fun readFile(fileName : String): List<String> {
        val resource = Thread.currentThread().contextClassLoader
            .getResource(fileName)
            ?: error("Resource not found: $fileName")

        return resource.readText().lines()
    }
}