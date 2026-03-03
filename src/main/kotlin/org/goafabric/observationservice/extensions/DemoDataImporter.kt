package org.goafabric.observationservice.extensions

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import jakarta.transaction.Transactional
import org.goafabric.observationservice.devices.logic.GDTImportLogic
import org.goafabric.observationservice.laboratory.logic.LDTImportLogic
import org.goafabric.observationservice.vitalsign.controller.dto.*
import org.goafabric.observationservice.vitalsign.logic.VitalSignLogic
import org.goafabric.observationservice.vitalsign.persistence.PatientRepository
import java.time.LocalDateTime

@ApplicationScoped
@Transactional
class DemoDataImporter(
    val vitalSignLogic: VitalSignLogic,
    val gdtImportLogic: GDTImportLogic,
    val ldtImportLogic: LDTImportLogic,
    val patientRepository: PatientRepository
) {

    var alreadyRun: Boolean = false

    fun onStart(@Observes ev: StartupEvent) {
        run()
    }

    fun run(vararg args: String) {
        if (alreadyRun) return
        createHomer()
        createMarge()
        createBart()
        createLisa()
        alreadyRun = true
    }

    private fun createHomer() {
        val homer = patientRepository.save(Patient("1", firstName = "Homer", lastName = "Simpson"))
        vitalSignLogic.save(
            BodyHeight(subject = "Patient/${homer.id}", valueQuantity = ValueQuantityCm(value = 180))
        )
        vitalSignLogic.save(
            BodyWeight(subject = "Patient/${homer.id}", valueQuantity = ValueQuantityKg(value = 140))
        )
        vitalSignLogic.save(
            BloodPressure(
                subject = "Patient/${homer.id}",
                effectiveDateTime = LocalDateTime.now(),
                valueQuantity = ValueQuantityMmHg(value = 140),
                //systolic = 140,
                //diastolic = 80
            )
        )

        gdtImportLogic.import("gdt/bloodpressure-homer.gdt")
        ldtImportLogic.import("ldt/laboratory-homer.ldt", "1")
    }

    private fun createMarge() {
        val marge = patientRepository.save(Patient(id = "2", firstName = "Marge", lastName = "Simpson"))
        vitalSignLogic.save(
            BodyHeight(subject = "Patient/${marge.id}", valueQuantity = ValueQuantityCm(value = 170))
        )
        vitalSignLogic.save(
            BodyWeight(subject = "Patient/${marge.id}", valueQuantity = ValueQuantityKg(value = 72))
        )
        vitalSignLogic.save(
            BloodPressure(
                subject = "Patient/${marge.id}",
                effectiveDateTime = LocalDateTime.now(),
                valueQuantity = ValueQuantityMmHg(value = 90),
                //systolic = 90,
                //diastolic = 70
            )
        )

        gdtImportLogic.import("gdt/bloodpressure-marge.gdt")
        ldtImportLogic.import("ldt/laboratory-marge.ldt", "2")
    }

    private fun createBart() {
        val bart = patientRepository.save(Patient(id = "3", firstName = "Bart", lastName = "Simpson"))
        vitalSignLogic.save(
            BodyHeight(subject = "Patient/${bart.id}", valueQuantity = ValueQuantityCm(value = 160))
        )
        vitalSignLogic.save(
            BodyWeight(subject = "Patient/${bart.id}", valueQuantity = ValueQuantityKg(value = 50))
        )
        vitalSignLogic.save(
            BloodPressure(
                subject = "Patient/${bart.id}", effectiveDateTime = LocalDateTime.now(),
                valueQuantity = ValueQuantityMmHg(value = 100),
                //systolic = 100, diastolic = 60
            )
        )
    }

    private fun createLisa() {
        val lisa = patientRepository.save(Patient(id = "4", firstName = "Lisa", lastName = "Simpson"))
        vitalSignLogic.save(
            BodyHeight(subject = "Patient/${lisa.id}", valueQuantity = ValueQuantityCm(value = 150))
        )
        vitalSignLogic.save(
            BodyWeight(subject = "Patient/${lisa.id}", valueQuantity = ValueQuantityKg(value = 45))
        )
        vitalSignLogic.save(
            BloodPressure(
                subject = "Patient/${lisa.id}", effectiveDateTime = LocalDateTime.now(),
                valueQuantity = ValueQuantityMmHg(value = 101),
                //systolic = 101, diastolic = 61
            )
        )
    }

}