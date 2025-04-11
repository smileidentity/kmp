package com.smileidentity.kmp.config



/**
 * Expected platform-specific implementation for initializing Smile Identity SDK.
 */
expect object SmileIdentityPlatform {
    fun initPlatform(config: SmileIdentityConfig)
}
