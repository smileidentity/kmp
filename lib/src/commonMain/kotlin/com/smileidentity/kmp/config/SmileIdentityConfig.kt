package com.smileidentity.kmp.config

/**
 * Configuration settings for Smile Identity SDK.
 *
 * @property authToken The API key for authentication.
 * @property environment The operating environment (PRODUCTION or SANDBOX).
 */
data class SmileIdentityConfig(
    val authToken: String,
    val environment: Environment,
    val productionLambdaUrl: String,
    val testLambdaUrl: String,
    val partnerId: String
)


/**
 * Enum representing available environments for the Smile Identity SDK.
 */
enum class Environment {
    PRODUCTION, SANDBOX
}