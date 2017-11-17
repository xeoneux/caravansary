package com.xeoneux.caravansary.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        return objectMapper
    }

    @Bean
    fun objectWriter(objectMapper: ObjectMapper): ObjectWriter =
            objectMapper.writerWithDefaultPrettyPrinter()
}
