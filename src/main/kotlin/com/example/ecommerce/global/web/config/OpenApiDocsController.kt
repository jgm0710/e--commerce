package com.example.ecommerce.global.web.config

import org.springframework.core.io.ClassPathResource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OpenApiDocsController {

    @GetMapping("/open-api/docs")
    fun getOpenApiDocs(): ClassPathResource {
        return ClassPathResource("/api-document.yml")
    }

}