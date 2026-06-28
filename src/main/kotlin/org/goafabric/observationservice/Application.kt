package org.goafabric.observationservice

import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.annotations.QuarkusMain

@QuarkusMain
class Application
/*
class Application : QuarkusApplication {

    
    @Inject
    lateinit var runner: ObservationAgentRunner

    override fun run(vararg args: String?): Int {
        runner.run()
        return 0
    }

}

 */

fun main(args: Array<String>) {
    Quarkus.run(*args)
}
