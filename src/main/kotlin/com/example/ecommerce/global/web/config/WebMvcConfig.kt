package com.example.ecommerce.global.web.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Configuration
@EnableWebMvc
class WebMvcConfig : WebMvcConfigurer {

    /**
     * RequestBody Serialize Config
     */
    @Bean
    fun jsonCustomizer(): MappingJackson2HttpMessageConverter {
        val mappingJackson2HttpMessageConverter = MappingJackson2HttpMessageConverter()
        mappingJackson2HttpMessageConverter.objectMapper = objectMapper()
        return mappingJackson2HttpMessageConverter
    }

    /**
     * JSON Field Serialize Config
     */
    @Bean
    fun objectMapper(): ObjectMapper {
        return jacksonObjectMapper()
            .registerModule(
                JavaTimeModule()
                    .addSerializer(
                        LocalDate::class.java, LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    )
                    .addSerializer(
                        Instant::class.java, InstantSerializer()
                    ),
            )
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    class InstantSerializer : JsonSerializer<Instant>() {
        override fun serialize(value: Instant?, gen: JsonGenerator?, serializers: SerializerProvider?) {
            if (gen != null) {
                return gen.writeString(DateTimeFormatter.ISO_INSTANT.format(value))
            }
        }
    }
}

