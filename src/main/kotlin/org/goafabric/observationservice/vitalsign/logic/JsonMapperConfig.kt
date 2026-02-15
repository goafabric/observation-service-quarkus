package org.goafabric.observationservice.vitalsign.logic

import com.fasterxml.jackson.databind.json.JsonMapper
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces

@ApplicationScoped
class JsonMapperConfig {
    @Produces
    fun jsonMapper() : JsonMapper {
        return JsonMapper()
    }
}