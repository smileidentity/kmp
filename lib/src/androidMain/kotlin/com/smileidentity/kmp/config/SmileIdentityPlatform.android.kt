package com.smileidentity.kmp.config

import android.content.Context
import com.smileidentity.SmileID


/**
 * Android-specific implementation for initializing the Smile Identity SDK.
 */
actual object SmileIdentityPlatform {
    private var isInitialized = false
    private var appContext: Context? = null

    /**
     * Sets the application context, ensuring it's available for SDK initialization.
     */
    fun setApplicationContext(context: Context) {
        if (appContext == null) {
            appContext = context.applicationContext
        }
    }

    /**
     * Initializes the SDK with the provided configuration.
     */
    actual fun initPlatform(config: SmileIdentityConfig) {
        if (!isInitialized) {
            val context = appContext
                ?: throw IllegalStateException("Application context not set. Make sure SmileIdentityInitProvider is registered.")
            SmileID.initialize(context)
            isInitialized = true
        }
    }
}
