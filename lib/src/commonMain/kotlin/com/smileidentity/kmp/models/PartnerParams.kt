package com.smileidentity.kmp.models

enum class KmpJobType(val value: Int) {
    BiometricKyc(1),
    SmartSelfieAuthentication(2),
    SmartSelfieEnrollment(4),
    EnhancedKyc(5),
    DocumentVerification(6),
    BVN(7),
    EnhancedDocumentVerification(11),

    /**
     * Special value used to indicate that the value returned from the server is not yet supported
     * by the SDK. Please update the SDK to the latest version to support the latest values.
     */
    Unknown(-1),
    ;

    companion object {
        fun fromValue(value: Int): KmpJobType = entries.find { it.value == value } ?: Unknown
    }
}


/**
 * Custom values specific to partners can be placed in [extras]
 */
data class KmpPartnerParams(
    val jobType: KmpJobType? = null,
    val jobId: String,
    val userId: String,
    val extras: Map<String?, String?>? = null,
)