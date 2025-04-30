package com.smileidentity.kmp.models

/**
 * This represents the KmpEnhancedKycRequest
 */
data class KmpEnhancedKycRequest(
    val country: String,
    val idType: String,
    val idNumber: String,
    val firstName: String? = null,
    val middleName: String? = null,
    val lastName: String? = null,
    val dob: String? = null,
    val phoneNumber: String? = null,
    val bankCode: String? = null,
    val callbackUrl: String? = null,
    val partnerParams: KmpPartnerParams,
    val timestamp: String,
    val signature: String,
    val consentInformation: KmpConsentInformation? = null,
)

/**
 * This represents the KmpEnhancedKycAsyncResponse
 */
data class KmpEnhancedKycAsyncResponse(val success: Boolean)