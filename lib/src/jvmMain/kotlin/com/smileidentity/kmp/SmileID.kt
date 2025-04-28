package com.smileidentity.kmp

import com.smileidentity.kmp.models.KmpConfig

actual class SmileID {

    actual fun initialize(
        config: KmpConfig?,
        useSandbox: Boolean,
        enableCrashReporting: Boolean,
    ) {
        // TODO : implement initialization for jvm targets
    }

    actual fun setCallbackUrl(callbackUrl: String) {
        // TODO : implement setCallbackUrl for iOS targets
    }

    actual fun setAllowOfflineMode(allowOfflineMode: Boolean) {
        // TODO : implement setAllowOfflineMode for iOS targets
    }

    actual fun getSubmittedJobs(): List<String> {
        // TODO : implement getSubmittedJobs for iOS targets
        return emptyList()
    }

    actual fun getUnsubmittedJobs(): List<String> {
        // TODO : implement getUnsubmittedJobs for iOS targets
        return emptyList()
    }

    actual fun cleanup(jobId: String) {
        // TODO : implement cleanup for iOS targets
    }

    actual fun cleanupJobs(jobIds: List<String>) {
        // TODO : implement cleanupJobs for iOS targets
    }

    actual fun submitJob(jobId: String, deleteFilesOnSuccess: Boolean) {
        // TODO : implement submitJob for iOS targets
    }
}