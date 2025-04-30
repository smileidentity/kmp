package com.smileidentity.kmp.models


data class KmpConsentInformation(
    val consentGrantedDate: String,
    val personalDetailsConsentGranted: Boolean,
    val contactInfoConsentGranted: Boolean,
    val documentInfoConsentGranted: Boolean,
)