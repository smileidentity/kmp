package com.smileidentity.kmp.enhanced_document_verification.models

import kotlinx.serialization.Serializable

@Serializable
data class CheckIDAPIStatusRequest(
  val partner_id: String,
  val signature: String,
  val timestamp: String,
  val country: String,
  val id_type: String,
  val environment: String,
)
