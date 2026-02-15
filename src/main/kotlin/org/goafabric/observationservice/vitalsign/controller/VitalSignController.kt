package org.goafabric.observationservice.vitalsign.controller

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import org.goafabric.observationservice.vitalsign.logic.VitalSignLogic
import org.goafabric.observationservice.vitalsign.persistence.entity.VitalSignEo

@Path("/vital-sign")
@Produces(MediaType.APPLICATION_JSON)

class VitalSignController(
    val vitalSignLogic: VitalSignLogic) {

    @GET
    @Path("/")
    fun getObservations() : Iterable<VitalSignEo> {
        return vitalSignLogic.getObservations()
    }

    @GET
    @Path("byPatientId")
    fun getObservations(@QueryParam("patientId") patientId : String) : Iterable<VitalSignEo> {
        return vitalSignLogic.getByPatientId(patientId)
    }

}