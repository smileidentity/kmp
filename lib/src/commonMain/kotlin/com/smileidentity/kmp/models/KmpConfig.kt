package com.smileidentity.kmp.models

/**
 * This represents the smile_config.json file that you can download from the Smile ID portal
 */
data class KmpConfig(
    val partnerId: String,
    val authToken: String,
    val prodLambdaUrl: String,
    val testLambdaUrl: String,
)

