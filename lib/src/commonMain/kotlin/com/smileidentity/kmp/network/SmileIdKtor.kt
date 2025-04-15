package com.smileidentity.kmp.network

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.smileidentity.kmp.config.BuildKonfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.statement.HttpResponse
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.appendIfNameAndValueAbsent
import kotlinx.serialization.json.Json

/**
 * A network client for interacting with the Smile ID Partner API.
 *
 * This client sets up a `Ktor` HTTP client with base configuration for all API calls, including the
 * base URL and authorization headers.
 */
class SmileIDClient {

    val apiClient = HttpClient {
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "prod.smileidentity.com"
            }

            headers.appendIfNameAndValueAbsent(
                "Authorization",
                "Bearer ${BuildKonfig.SMILE_ID_AUTH_TOKEN}",
            )
        }
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                })
        }
    }

    /** API call that handles both success and error responses */
    suspend inline fun <reified T> makeApiCall(
        apiCall: HttpClient.() -> HttpResponse
    ): Either<Throwable, T> {
        return try {
            val response = apiCall.invoke(apiClient)
            if (response.status.value in 200..299) {
                response.body<T>().right()
            } else {
                val errorMessage = response.body<String>()
                Throwable("Error: $errorMessage, Status Code: ${response.status.value}").left()
            }
        } catch (e: Throwable) {
            e.left()
        }
    }
}
