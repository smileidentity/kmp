package com.smileidentity.kmp.config


/**
 * JVM-specific implementation for initializing the Smile Identity SDK.
 */
actual object SmileIdentityPlatform {
    /**
     * Initializes the SDK for JVM.
     */
    actual fun initPlatform(config: SmileIdentityConfig) {
        println("Smile Identity SDK initialized for JVM with API key: ${config.authToken}")
    }
}