package com.smileidentity.kmp

import com.smileidentity.kmp.models.KmpAuthenticationRequest
import com.smileidentity.kmp.models.KmpAuthenticationResponse
import com.smileidentity.kmp.models.KmpConfig
import com.smileidentity.kmp.models.KmpEnhancedKycAsyncResponse
import com.smileidentity.kmp.models.KmpEnhancedKycRequest

expect class SmileID {

    fun initialize(
        context: PlatformDependency,
        config: KmpConfig? = null,
        useSandbox: Boolean = false,
        enableCrashReporting: Boolean = true,
    )

    fun setCallbackUrl(callbackUrl: String)

    fun setAllowOfflineMode(allowOfflineMode: Boolean)

    fun getSubmittedJobs(): List<String>

    fun getUnsubmittedJobs(): List<String>

    fun cleanup(jobId: String)

    fun cleanupJobs(jobIds: List<String>)

    fun submitJob(jobId: String, deleteFilesOnSuccess: Boolean)

    suspend fun authenticate(
        request: KmpAuthenticationRequest,
    ): KmpAuthenticationResponse

    suspend fun doEnhancedKycAsync(
        request: KmpEnhancedKycRequest,
    ): KmpEnhancedKycAsyncResponse
}
