package com.smileidentity.kmp.config

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized

/**
 * Smile Identity SDK main class responsible for initialization and configuration.
 */
class SmileIdentity private constructor(
    private val config: SmileIdentityConfig
) {

    companion object {
        private var instance: SmileIdentity? = null
        private val lock = Any()

        /**
         * Returns the singleton instance of Smile Identity SDK.
         * If not initialized, it creates a default instance.
         */
        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(): SmileIdentity {
            return instance ?: synchronized(lock as SynchronizedObject) {
                instance ?: createDefaultInstance().also { instance = it }
            }
        }

        /**
         * Creates a default SDK instance with placeholder configuration.
         * This should be overridden by proper user-provided configurations.
         */
        private fun createDefaultInstance(): SmileIdentity {
            val defaultConfig = SmileIdentityConfig(
                apiKey = BuildKonfig.SMILE_ID_API_KEY,
                environment = Environment.valueOf(BuildKonfig.SMILE_ID_ENVIRONMENT)
            )
            return SmileIdentity(defaultConfig).apply { initializeIfNeeded() }
        }
    }

    /**
     * Initializes the platform-specific implementation if not already initialized.
     */
    private fun initializeIfNeeded() {
        SmileIdentityPlatform.initPlatform(config)
    }


    /**
     * Builder pattern to allow developers to configure the SDK securely.
     */
    class Builder {
        private var apiKey: String? = null
        private var environment: Environment = Environment.PRODUCTION

        /**
         * Sets the API key for authentication.
         */
        fun setApiKey(apiKey: String) = apply { this.apiKey = apiKey }

        /**
         * Sets the operating environment (PRODUCTION or SANDBOX).
         */
        fun setEnvironment(env: Environment) = apply { this.environment = env }

        /**
         * Builds and initializes the Smile Identity SDK with the provided configuration.
         */
        fun build(): SmileIdentity {
            val finalConfig = SmileIdentityConfig(
                apiKey ?: throw IllegalArgumentException("API Key is required"), environment
            )
            return SmileIdentity(finalConfig).also { instance = it }
        }
    }
}
