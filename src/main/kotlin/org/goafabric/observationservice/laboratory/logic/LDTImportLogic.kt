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

    fun parse(lines: List<String>): MutableList<CObservation> {
        val observations = mutableListOf<CObservation>()

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
                        CObservation(subject = "Patient/${patientId}",
                            valueQuantity = ValueQuantityMol(value = valueQuantity.toDouble()),
                            code = Coding("http://loinc.org", "15074-8", "Glucose [Moles/volume] in Blood"),
                            effectiveDateTime = LocalDate.parse(
                                currentDate,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            ).atStartOfDay()
                        ))
                    "CHOL" -> observations.add(
                        CObservation(subject = "Patient/${patientId}",
                            valueQuantity = ValueQuantityMol(value = valueQuantity.toDouble()),
                            code = Coding("http://loinc.org", "2098-6", "Cholesterol [Moles/Volume]"),
                            effectiveDateTime = LocalDate.parse(
                                currentDate,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            ).atStartOfDay()
                        ))
                    "HDL" -> observations.add(
                        CObservation(
                            subject = "Patient/${patientId}",
                            valueQuantity = ValueQuantityMol(value = valueQuantity.toDouble()),
                            code = Coding("http://loinc.org", "2086-7", "Cholesterol Hdl [Moles/Volume]"),
                            effectiveDateTime = LocalDate.parse(
                                currentDate,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            ).atStartOfDay()
                        )
                    )
                    "LDL" -> observations.add(
                        CObservation(
                            subject = "Patient/${patientId}",
                            valueQuantity = ValueQuantityMol(value = valueQuantity.toDouble()),
                            code = Coding("http://loinc.org", "18262-6", "Cholesterol Ldl [Moles/Volume]"),
                            effectiveDateTime = LocalDate.parse(
                                currentDate,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            ).atStartOfDay()
                        )
                    )
                    "HbA1c" -> observations.add(
                        CObservation(
                            subject = "Patient/${patientId}",
                            valueQuantity = ValueQuantityPercent(value = valueQuantity.toDouble()),
                            code = Coding("http://loinc.org", "4548-4", "Hemoglobin [Moles/Volume]"),
                            effectiveDateTime = LocalDate.parse(
                                currentDate,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            ).atStartOfDay()
                        )
                    )
                    "AST" -> observations.add(
                        CObservation(
                            subject = "Patient/${patientId}",
                            valueQuantity = ValueQuantityEnzyme(value = valueQuantity.toDouble()),
                            code = Coding("http://loinc.org", "1920-8", "Aspartate aminotransferase (AST) [Enzymatic activity/volume] in Serum or Plasma"),
                            effectiveDateTime = LocalDate.parse(
                                currentDate,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            ).atStartOfDay()
                        )
                    )
                    "ALT" -> observations.add(
                        CObservation(
                            subject = "Patient/${patientId}",
                            valueQuantity = ValueQuantityEnzyme(value = valueQuantity.toDouble()),
                            code = Coding("http://loinc.org", "1742-6", "Alanine aminotransferase (ALT) [Enzymatic activity/volume] in Serum or Plasma"),
                            effectiveDateTime = LocalDate.parse(
                                currentDate,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            ).atStartOfDay()
                        )
                    )
                    "TSH" -> observations.add(
                        CObservation(
                            subject = "Patient/${patientId}",
                            valueQuantity = ValueQuantityIU(value = valueQuantity.toDouble()),
                            code = Coding("http://loinc.org", "11579-0", "Thyrotropin (TSH) [Units/volume] in Serum or Plasma"),
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