package org.example.project.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(): HttpClient {
    return HttpClient { // Ktor will pick the appropriate engine for each platform
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true // Very important for APIs that might add new fields
            })
        }
        install(Logging) {
            level = LogLevel.ALL // Log everything: INFO, HEADERS, BODY
            logger = object : Logger {
                override fun log(message: String) {
                    println("Ktor HTTP Client: $message") // Simple println logger
                }
            }
        }
        // Default request configuration can be added here if needed
        // install(DefaultRequest) { ... }
    }
}

// You could also define it as a singleton if preferred, though a factory function is fine
// val httpClient: HttpClient by lazy { createHttpClient() }
