package com.smileidentity.kmp.presentation.enhanced_document_verification

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import com.smileidentity.kmp.utils.getCurrentIsoTimestamp
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

expect class KmpFile


@Serializable
data class ComposeConsentContent(
    @SerialName("consent_granted_date") val consentGrantedDate: String,
    @SerialName("personal_details_consent_granted") val personalDetailsConsentGranted: Boolean,
    @SerialName("contact_information_consent_granted") val contactInfoConsentGranted: Boolean,
    @SerialName("document_information_consent_granted") val documentInfoConsentGranted: Boolean
) {
    companion object {
        fun default() = ComposeConsentContent(
            consentGrantedDate = getCurrentIsoTimestamp(),
            personalDetailsConsentGranted = false,
            contactInfoConsentGranted = false,
            documentInfoConsentGranted = false
        )
    }
}

@Serializable
data class EnhancedDocumentVerificationResult(
    @SerialName("selfie_file") val selfieFile: KmpFile,
    @SerialName("document_front_file") val documentFrontFile: KmpFile,
    @SerialName("liveness_files") val livenessFiles: List<KmpFile>? = null,
    @SerialName("document_back_file") val documentBackFile: KmpFile? = null,
    @SerialName("did_submit_enhanced_doc_v_job") val didSubmitEnhancedDocVJob: Boolean,
)

@Composable
expect fun getSmileIDColorScheme(): ColorScheme

@Composable
expect fun getSmileIDTypography(): Typography


/**
 * The result of an SDK flow invocation.
 *
 * This is a sealed class with two subclasses:
 * - [Success]: indicates the flow was successful, containing a value of type [T].
 * - [Error]: indicates an error occurred during the flow.
 *
 * Instead of using Parcelable, we now use kotlinx.serialization to support serializability.
 * For the error case, we capture the throwable's message.
 */
@Serializable
sealed class SmileIDResult<out T> {

    /**
     * Represents a successful result.
     *
     * NB! The Job itself may or may not be complete yet. This can be checked with other job status
     * information outside of this result.
     */
    @Serializable
    @SerialName("success")
    data class Success<T>(
        val data: T
    ) : SmileIDResult<T>()

    /**
     * Represents an error encountered during the flow.
     *
     * Instead of storing the entire Throwable, which is non-serializable, we store its
     * error message.
     */
    @Serializable
    @SerialName("error")
    data class Error(
        val errorMessage: String
    ) : SmileIDResult<Nothing>()
}


