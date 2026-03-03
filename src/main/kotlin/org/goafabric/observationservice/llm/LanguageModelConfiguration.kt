package org.goafabric.observationservice.llm

import dev.langchain4j.memory.chat.MessageWindowChatMemory
import dev.langchain4j.model.chat.ChatModel
import dev.langchain4j.model.ollama.OllamaChatModel
import dev.langchain4j.model.openai.OpenAiChatModel
import dev.langchain4j.service.AiServices
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import java.time.Duration

@ApplicationScoped
class LanguageModelConfiguration {

    @Produces
    //@Profile("ollama")
    fun chatModelOllama(): ChatModel {
        return OllamaChatModel.builder()
            .baseUrl("http://localhost:11434")
            .modelName("llama3.1")
            .timeout(Duration.ofSeconds(60)).temperature(0.0)
            .build()
    }


    fun chatModelOpenAi(): ChatModel {
        return OpenAiChatModel.builder().apiKey("")
            .baseUrl("http://localhost:11434/v1")
            .modelName("gpt-oss:20b")
            .timeout(Duration.ofSeconds(60)).temperature(0.0)
            .build()
    }

    @Produces
    fun databaseAgent(chatLanguageModel: ChatModel?, observationAdapter: ObservationAdapter?): ObservationAgent? {
        return AiServices.builder<ObservationAgent?>(ObservationAgent::class.java)
            .chatModel(chatLanguageModel)
            .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
            .tools(io.quarkus.arc.ClientProxy.unwrap(observationAdapter))
            .build()
    }
}
