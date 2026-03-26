package org.goafabric.observationservice.laboratory.controller

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import org.goafabric.observationservice.laboratory.controller.dto.LaboratoryData
import org.goafabric.observationservice.laboratory.logic.LaboratoryDataLogic

@Path("/laboratory-data")
@Produces(MediaType.APPLICATION_JSON)
class LaboratoryDataController(
    val laboratoryLogic: LaboratoryDataLogic) {

    @GET
    @Path("byPatientId")
    fun getObservations(@QueryParam("patientId") patientId : String) : List<LaboratoryData> {
        return laboratoryLogic.getByPatientId(patientId)
    }

}