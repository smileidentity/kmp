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
                authToken = BuildKonfig.SMILE_ID_AUTH_TOKEN,
                environment = Environment.valueOf(BuildKonfig.SMILE_ID_ENVIRONMENT),
                productionLambdaUrl = BuildKonfig.SMILE_ID_PROD_LAMBDA_URL,
                testLambdaUrl = BuildKonfig.SMILE_ID_TEST_LAMBDA_URL,
                partnerId = BuildKonfig.SMILE_ID_PARTNER_ID
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
        private var authToken: String? = null
        private var environment: Environment = Environment.PRODUCTION
        private var productionLambdaUrl: String? = null
        private var testLambdaUrl: String? = null
        private var partnerId: String? = null

        /**
         * Sets the Auth Token for authentication.
         */
        fun setAuthTokenKey(authToken: String) = apply { this.authToken = authToken }

        /**
         * Sets the Partner Id .
         */
        fun setPartnerId(partnerId: String) = apply { this.partnerId = partnerId }

        /**
         * Sets the operating environment (PRODUCTION or SANDBOX).
         */
        fun setEnvironment(env: Environment) = apply { this.environment = env }


        /**
         * Sets the production Lambda URL
         */
        fun setProductionLambdaUrl(productionLambdaUrl: String) =
            apply { this.productionLambdaUrl = productionLambdaUrl }


        /**
         * Sets the test Lambda URL
         */
        fun setLambdaUrl(testLambdaUrl: String) = apply { this.testLambdaUrl = testLambdaUrl }

        /**
         * Builds and initializes the Smile Identity SDK with the provided configuration.
         */
        fun build(): SmileIdentity {
            val finalConfig = SmileIdentityConfig(
                authToken = authToken ?: throw IllegalArgumentException("Auth Token is required"),
                environment = environment,
                productionLambdaUrl = productionLambdaUrl.toString(),
                testLambdaUrl = testLambdaUrl.toString(),
                partnerId = partnerId ?: throw IllegalArgumentException("Partner ID is required")
            )
            return SmileIdentity(finalConfig).also { instance = it }
        }
    }
}
