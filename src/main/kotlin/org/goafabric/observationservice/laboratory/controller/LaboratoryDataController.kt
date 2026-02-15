package org.goafabric.observationservice.laboratory.controller

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import org.goafabric.observationservice.laboratory.logic.LaboratoryDataLogic
import org.goafabric.observationservice.laboratory.persistence.entity.LaboratoryDataEo

@Path("/laboratory-data")
@Produces(MediaType.APPLICATION_JSON)
class LaboratoryDataController(
    val laboratoryLogic: LaboratoryDataLogic) {

    @GET
    @Path("byPatientId")
    fun getObservations(@QueryParam("patientId") patientId : String) : List<LaboratoryDataEo> {
        return laboratoryLogic.getByPatientId(patientId)
    }

}