package com.smileidentity.kmp

import com.smileidentity.kmp.models.KmpAuthenticationRequest
import com.smileidentity.kmp.models.KmpAuthenticationResponse
import com.smileidentity.kmp.models.KmpConfig
import com.smileidentity.kmp.models.KmpEnhancedKycAsyncResponse
import com.smileidentity.kmp.models.KmpEnhancedKycRequest

actual object SmileID {

    actual fun initialize(
        config: KmpConfig?,
        useSandbox: Boolean,
        enableCrashReporting: Boolean,
    ) {
        // TODO : implement initialization for linux targets
    }

    actual fun setCallbackUrl(callbackUrl: String) {
        // TODO : implement setCallbackUrl for linux targets
    }

    actual fun setAllowOfflineMode(allowOfflineMode: Boolean) {
        // TODO : implement setAllowOfflineMode for linux targets
    }

    actual fun getSubmittedJobs(): List<String> {
        // TODO : implement getSubmittedJobs for linux targets
        return emptyList()
    }

    actual fun getUnsubmittedJobs(): List<String> {
        // TODO : implement getUnsubmittedJobs for linux targets
        return emptyList()
    }

    actual fun cleanup(jobId: String) {
        // TODO : implement cleanup for linux targets
    }

    actual fun cleanupJobs(jobIds: List<String>) {
        // TODO : implement cleanupJobs for linux targets
    }

    actual fun submitJob(jobId: String, deleteFilesOnSuccess: Boolean) {
        // TODO : implement submitJob for linux targets
    }

    actual suspend fun authenticate(
        request: KmpAuthenticationRequest,
    ): KmpAuthenticationResponse {
        // TODO : implement authenticate for linux targets
    }

    actual suspend fun doEnhancedKycAsync(
        request: KmpEnhancedKycRequest,
    ): KmpEnhancedKycAsyncResponse {
        // TODO : implement doEnhancedKycAsync for linux targets
    }
}
