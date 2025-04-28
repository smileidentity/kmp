package com.smileidentity.kmp

import com.smileidentity.SmileID
import com.smileidentity.kmp.mapper.toRequest
import com.smileidentity.kmp.mapper.toResponse
import com.smileidentity.kmp.models.KmpAuthenticationRequest
import com.smileidentity.kmp.models.KmpConfig
import com.smileidentity.kmp.models.KmpEnhancedKycRequest
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

actual object SmileID {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    actual fun initialize(
        context: PlatformDependency,
        config: KmpConfig?,
        useSandbox: Boolean,
        enableCrashReporting: Boolean,
    ) {
        config?.let {
            SmileID.initialize(
                context = context.context,
                config = it.toRequest(),
                useSandbox = useSandbox,
                enableCrashReporting = enableCrashReporting
            )
        } ?: SmileID.initialize(
            context = context.context,
            useSandbox = useSandbox,
            enableCrashReporting = enableCrashReporting
        )
    }

    actual fun setCallbackUrl(callbackUrl: String) =
        SmileID.setCallbackUrl(callbackUrl = URL(callbackUrl))

    actual fun setAllowOfflineMode(allowOfflineMode: Boolean) =
        SmileID.setAllowOfflineMode(allowOfflineMode = allowOfflineMode)

    actual fun getSubmittedJobs(): List<String> = SmileID.getSubmittedJobs()

    actual fun getUnsubmittedJobs(): List<String> = SmileID.getUnsubmittedJobs()

    actual fun cleanup(jobId: String) = SmileID.cleanup(jobId = jobId)

    actual fun cleanupJobs(jobIds: List<String>) = SmileID.cleanup(jobIds = jobIds)

    actual fun submitJob(jobId: String, deleteFilesOnSuccess: Boolean) {
        scope.launch {
            SmileID.submitJob(jobId = jobId, deleteFilesOnSuccess = deleteFilesOnSuccess)
        }
    }

    actual suspend fun authenticate(request: KmpAuthenticationRequest) =
        SmileID.api.launch { authenticate(request.toRequest()).toResponse() }

    actual suspend fun doEnhancedKycAsync(request: KmpEnhancedKycRequest) =
        SmileID.api.launch { doEnhancedKycAsync(request.toRequest()).toResponse() }
}

/**
 * Executes a suspending operation on SmileID.api within the IO dispatcher.
 */
private suspend fun <T, R> T.launch(block: suspend T.() -> R): R {
    return withContext(Dispatchers.IO) {
        block()
    }
}

/**
 * Launches a new coroutine in the specified dispatcher (IO by default) and returns the result to
 * the callback. Used for launching coroutines from Dart.
 */
private fun <T> launch(
    work: suspend () -> T,
    callback: (Result<T>) -> Unit,
    scope: CoroutineScope = CoroutineScope(Dispatchers.IO),
) {
    val handler =
        CoroutineExceptionHandler { _, throwable ->
            callback.invoke(Result.failure(throwable))
        }
    scope.launch(handler) {
        callback.invoke(Result.success(work()))
    }
}
