package com.smileidentity.kmp.config

import android.content.Context
import com.smileidentity.SmileID
import com.smileidentity.models.Config


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

            SmileID.initialize(
                context = context, config = Config(
                    partnerId = config.partnerId,
                    authToken = config.authToken,
                    prodLambdaUrl = config.productionLambdaUrl,
                    testLambdaUrl = config.testLambdaUrl
                )
            )
            isInitialized = true
        }
    }
}
