package org.goafabric.observationservice.laboratory.logic

import jakarta.enterprise.context.ApplicationScoped
import org.goafabric.observationservice.laboratory.controller.dto.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@ApplicationScoped
class LDTImportLogic(
        val laboratoryLogic: LaboratoryDataLogic) {

    fun import(fileName: String, patiendId: String) {
        val lines = readFile(fileName).drop(1)
        val observations = parse(lines)
        laboratoryLogic.save(observations, patiendId)
    }

    fun parse(lines: List<String>): MutableList<Observation> {
        val observations = mutableListOf<Observation>()

        for (line in lines) {
            val fields = line.split(",")
            if (fields.size == 11) {
                val patientId = fields[0]
                val type = fields[4]
                val valueQuantity = fields[6]
                val range = fields[8]
                val currentDate = fields[9]

                when (type) {
                    "GLU" -> observations.add(
                        Glucose(subject = "Patient/${patientId}",
                            valueQuantity = ValueQuantityMol(value = valueQuantity.toDouble()),
                            effectiveDateTime = LocalDate.parse(
                                currentDate,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            ).atStartOfDay()
                        ))
                    "CHOL" -> observations.add(
                        CholesterolTotal(subject = "Patient/${patientId}",
                            valueQuantity = ValueQuantityMol(value = valueQuantity.toDouble()),
                            effectiveDateTime = LocalDate.parse(
                                currentDate,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            ).atStartOfDay()
                        ))
                    "HDL" -> observations.add(
                        CholesterolHdl(
                            subject = "Patient/${patientId}",
                            valueQuantity = ValueQuantityMol(value = valueQuantity.toDouble()),
                            effectiveDateTime = LocalDate.parse(
                                currentDate,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            ).atStartOfDay()
                        )
                    )
                    "LDL" -> observations.add(
                        CholesterolLdl(
                            subject = "Patient/${patientId}",
                            valueQuantity = ValueQuantityMol(value = valueQuantity.toDouble()),
                            effectiveDateTime = LocalDate.parse(
                                currentDate,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            ).atStartOfDay()
                        )
                    )
                    "HbA1c" -> observations.add(
                        HemoGlobin(
                            subject = "Patient/${patientId}",
                            valueQuantity = ValueQuantityPercent(value = valueQuantity.toDouble()),
                            effectiveDateTime = LocalDate.parse(
                                currentDate,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            ).atStartOfDay()
                        )
                    )
                    "AST" -> observations.add(
                        Ast(
                            subject = "Patient/${patientId}",
                            valueQuantity = ValueQuantityEnzyme(value = valueQuantity.toDouble()),
                            effectiveDateTime = LocalDate.parse(
                                currentDate,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            ).atStartOfDay()
                        )
                    )
                    "ALT" -> observations.add(
                        Alt(
                            subject = "Patient/${patientId}",
                            valueQuantity = ValueQuantityEnzyme(value = valueQuantity.toDouble()),
                            effectiveDateTime = LocalDate.parse(
                                currentDate,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            ).atStartOfDay()
                        )
                    )
                    "TSH" -> observations.add(
                        Thyrotropin(
                            subject = "Patient/${patientId}",
                            valueQuantity = ValueQuantityIU(value = valueQuantity.toDouble()),
                            effectiveDateTime = LocalDate.parse(
                                currentDate,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            ).atStartOfDay()
                        )
                    )

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