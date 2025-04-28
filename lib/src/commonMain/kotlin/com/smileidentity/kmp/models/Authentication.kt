package com.smileidentity.kmp.models

/**
 * The Auth Smile request. Auth Smile serves multiple purposes:
 *
 * - It is used to fetch the signature needed for subsequent API requests
 * - It indicates the type of job that will being performed
 * - It is used to fetch consent information for the partner
 *
 * [jobType] The type of job that will be performed
 * [country] The country code of the country where the job is being performed. This value is
 * required in order to get back consent information for the partner
 * [idType] The type of ID that will be used for the job. This value is required in order to
 * get back consent information for the partner
 * [updateEnrolledImage] Whether or not the enrolled image should be updated with image
 * submitted for this job
 * [jobId] The job ID to associate with the job. Most often, this will correspond to a unique
 * Job ID within your own system. If not provided, a random job ID will be generated
 * [userId] The user ID to associate with the job. Most often, this will correspond to a unique
 * User ID within your own system. If not provided, a random user ID will be generated
 *
 */
data class KmpAuthenticationRequest(
    val jobType: KmpJobType,
    val country: String? = null,
    val idType: String? = null,
    val updateEnrolledImage: Boolean? = null,
    val jobId: String? = null,
    val userId: String? = null,
)

data class KmpAuthenticationResponse(
    val success: Boolean,
    val signature: String,
    val timestamp: String,
    val partnerParams: KmpPartnerParams,
    val callbackUrl: String? = null,
    val consentInfo: KmpConsentInformation? = null,
)