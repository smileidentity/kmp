package com.smileidentity.kmp.config

/**
 * Configuration settings for Smile Identity SDK.
 *
 * @property apiKey The API key for authentication.
 * @property environment The operating environment (PRODUCTION or SANDBOX).
 */
data class SmileIdentityConfig(
    val apiKey: String, val environment: Environment
)


/**
 * Enum representing available environments for the Smile Identity SDK.
 */
enum class Environment {
    PRODUCTION, SANDBOX
}