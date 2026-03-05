package org.goafabric.observationservice.llm

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.goafabric.observationservice.extensions.DemoDataImporter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

@ApplicationScoped
class ObservationAgentRunner(
    val demoDataImporter: DemoDataImporter,
    val observationAgent: ObservationAgent) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    fun run() {
            demoDataImporter.run()
            try {
                val scanner = Scanner(System.`in`)
                while (true) {
                    println("[User]: ")
                    println("[Agent]: " + observationAgent.chat(scanner.nextLine()));
                }
            } catch (e: Exception) {
                log.error("error", e)
            }
        }

}
