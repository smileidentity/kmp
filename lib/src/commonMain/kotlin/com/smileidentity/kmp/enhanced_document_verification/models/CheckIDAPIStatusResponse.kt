package com.smileidentity.kmp.enhanced_document_verification.models

import kotlinx.serialization.Serializable

@Serializable
data class CheckIDAPIStatusResponse(
    val last_checked: String,
    val last_check_status: String,
    val last_hour_success_rate: String,
    val last_known_status: String,
    val last_check_success_rate: String,
)