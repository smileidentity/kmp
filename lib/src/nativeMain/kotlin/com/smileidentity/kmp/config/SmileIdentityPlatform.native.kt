package com.smileidentity.kmp.config


/**
 * iOS-specific implementation for initializing the Smile Identity SDK.
 */
actual object SmileIdentityPlatform {
    /**
     * Initializes the SDK for iOS.
     */
    actual fun initPlatform(config: SmileIdentityConfig) {
        println("Smile Identity SDK initialized for iOS with API key: ${config.apiKey}")
    }
}