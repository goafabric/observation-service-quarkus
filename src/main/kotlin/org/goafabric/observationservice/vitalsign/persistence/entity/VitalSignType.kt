package org.goafabric.observationservice.vitalsign.persistence.entity

//as an alternative we could directly use the LOINC Codes
enum class VitalSignType {
    BLOOD_PRESSURE,
    BLOOD_PRESSURE_SERIES,
    BODY_HEIGHT,
    BODY_WEIGHT
}