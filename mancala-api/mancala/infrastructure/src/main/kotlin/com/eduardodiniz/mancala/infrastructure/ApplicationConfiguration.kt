package com.eduardodiniz.mancala.infrastructure

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@Configuration
class ApplicationConfiguration {
    @Bean
    @Primary
    fun objectMapper(): ObjectMapper {
        return JsonMapper.builder()
                .propertyNamingStrategy(PropertyNamingStrategies.SnakeCaseStrategy())
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .addModule(
                        KotlinModule.Builder()
                                .configure(KotlinFeature.NullToEmptyCollection, false)
                                .configure(KotlinFeature.NullToEmptyMap, false)
                                .configure(KotlinFeature.NullIsSameAsDefault, false)
                                .configure(KotlinFeature.StrictNullChecks, false)
                                .build()
                )
                .build()
    }
}