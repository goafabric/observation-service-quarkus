package org.goafabric.observationservice

import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain
import jakarta.inject.Inject
import org.goafabric.observationservice.llm.ObservationAgentRunner

@QuarkusMain
class Application : QuarkusApplication {
    @Inject
    lateinit var runner: ObservationAgentRunner

    override fun run(vararg args: String?): Int {
        runner.run()
        return 0
    }

}

fun main(args: Array<String>) {
    Quarkus.run(*args)
}
