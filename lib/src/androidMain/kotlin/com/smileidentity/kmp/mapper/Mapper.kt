package com.smileidentity.kmp.mapper

import com.smileidentity.kmp.models.KmpAuthenticationRequest
import com.smileidentity.kmp.models.KmpAuthenticationResponse
import com.smileidentity.kmp.models.KmpConfig
import com.smileidentity.kmp.models.KmpConsentInformation
import com.smileidentity.kmp.models.KmpEnhancedKycAsyncResponse
import com.smileidentity.kmp.models.KmpEnhancedKycRequest
import com.smileidentity.kmp.models.KmpJobType
import com.smileidentity.kmp.models.KmpPartnerParams
import com.smileidentity.models.AuthenticationRequest
import com.smileidentity.models.AuthenticationResponse
import com.smileidentity.models.Config
import com.smileidentity.models.ConsentInformation
import com.smileidentity.models.EnhancedKycAsyncResponse
import com.smileidentity.models.EnhancedKycRequest
import com.smileidentity.models.JobType
import com.smileidentity.models.PartnerParams

fun convertNullableMapToNonNull(map: Map<String?, String?>?): Map<String, String> = map
    ?.filterKeys { it != null }
    ?.filterValues { it != null }
    ?.mapKeys { it.key!! }
    ?.mapValues { it.value!! } ?: mapOf()

fun convertNonNullMapToNullable(map: Map<String, String>): Map<String?, String?> = map
    .mapKeys { it.key }
    .mapValues { it.value }

fun KmpConfig.toRequest(): Config = Config(
    partnerId = partnerId,
    authToken = authToken,
    prodLambdaUrl = prodLambdaUrl,
    testLambdaUrl = testLambdaUrl,
)

fun KmpConsentInformation.toRequest() = ConsentInformation(
    consentGrantedDate = consentGrantedDate,
    personalDetailsConsentGranted = personalDetailsConsentGranted,
    contactInfoConsentGranted = contactInfoConsentGranted,
    documentInfoConsentGranted = documentInfoConsentGranted,
)

fun KmpJobType.toRequest() = when (this) {
    KmpJobType.BiometricKyc -> JobType.BiometricKyc
    KmpJobType.SmartSelfieAuthentication -> JobType.SmartSelfieAuthentication
    KmpJobType.SmartSelfieEnrollment -> JobType.SmartSelfieEnrollment
    KmpJobType.EnhancedKyc -> JobType.EnhancedKyc
    KmpJobType.DocumentVerification -> JobType.DocumentVerification
    KmpJobType.BVN -> JobType.BVN
    KmpJobType.EnhancedDocumentVerification -> JobType.EnhancedDocumentVerification
    KmpJobType.Unknown -> JobType.Unknown
}

fun JobType.toResponse() = when (this) {
    JobType.BiometricKyc -> KmpJobType.BiometricKyc
    JobType.SmartSelfieAuthentication -> KmpJobType.SmartSelfieAuthentication
    JobType.SmartSelfieEnrollment -> KmpJobType.SmartSelfieEnrollment
    JobType.EnhancedKyc -> KmpJobType.EnhancedKyc
    JobType.DocumentVerification -> KmpJobType.DocumentVerification
    JobType.BVN -> KmpJobType.BVN
    JobType.EnhancedDocumentVerification -> KmpJobType.EnhancedDocumentVerification
    JobType.Unknown -> KmpJobType.Unknown
}

fun KmpPartnerParams.toRequest() = PartnerParams(
    jobType = jobType?.toRequest(),
    jobId = jobId,
    userId = userId,
    extras = convertNullableMapToNonNull(extras),
)

fun PartnerParams.toResponse() = KmpPartnerParams(
    jobType = jobType?.toResponse(),
    jobId = jobId,
    userId = userId,
    extras = convertNonNullMapToNullable(extras),
)

fun KmpAuthenticationRequest.toRequest() = AuthenticationRequest(
    jobType = jobType.toRequest(),
    country = country,
    idType = idType,
    updateEnrolledImage = updateEnrolledImage,
    jobId = jobId,
    userId = userId,
)

fun AuthenticationResponse.toResponse() = KmpAuthenticationResponse(
    success = success,
    signature = signature,
    timestamp = timestamp,
    partnerParams = partnerParams.toResponse(),
    callbackUrl = callbackUrl,
)

fun KmpEnhancedKycRequest.toRequest() = EnhancedKycRequest(
    country = country,
    idType = idType,
    idNumber = idNumber,
    firstName = firstName,
    middleName = middleName,
    lastName = lastName,
    dob = dob,
    phoneNumber = phoneNumber,
    bankCode = bankCode,
    callbackUrl = callbackUrl,
    partnerParams = partnerParams.toRequest(),
    sourceSdk = "android (kmp)",
    timestamp = timestamp,
    signature = signature,
    consentInformation =
        consentInformation?.toRequest() ?: ConsentInformation(
            consentGrantedDate = "",
            personalDetailsConsentGranted = false,
            contactInfoConsentGranted = false,
            documentInfoConsentGranted = false,
        ),
)

fun EnhancedKycAsyncResponse.toResponse() = KmpEnhancedKycAsyncResponse(
    success = success,
)