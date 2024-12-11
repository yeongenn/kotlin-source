package com.yeongenn.kopringstudy.kopring.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI {
        val info = Info()
            .version(API_VERSION)
            .title(API_NAME)
            .description(API_DESCRIPTION)

        return OpenAPI()
            .info(info)
    }

    companion object {
        private const val API_NAME = "KP Library API"
        private const val API_VERSION = "0.0.1"
        private const val API_DESCRIPTION = "KP Library API 명세서"
    }
}