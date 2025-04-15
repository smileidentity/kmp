package com.smileidentity.kmp.enhanced_document_verification.api

import arrow.core.Either
import com.smileidentity.kmp.config.BuildKonfig
import com.smileidentity.kmp.enhanced_document_verification.models.CheckIDAPIStatusRequest
import com.smileidentity.kmp.enhanced_document_verification.models.CheckIDAPIStatusResponse
import com.smileidentity.kmp.network.SmileIDClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


/**
 * API Class for handling Enhanced Document Verification operations.
 *
 * This class utilizes the [SmileIDClient] to interact with Smile ID's backend services.
 * It provides functionality to check the status of supported ID types for a given country.
 */
class EnhancedDocumentVerificationApi {

  private val smileIDAPI = SmileIDClient()

  suspend fun checkIDApiStatus(
    data: CheckIDAPIStatusRequest
  ): Either<Throwable, CheckIDAPIStatusResponse> {

    return smileIDAPI.makeApiCall<CheckIDAPIStatusResponse> {
      post(CHECK_ID_API_STATUS) {
        contentType(ContentType.Application.Json)
        setBody(data)
        headers.append("Authorization", "Bearer ${BuildKonfig.SMILE_ID_AUTH_TOKEN}")
      }
    }
  }

  companion object {
    const val CHECK_ID_API_STATUS = "/id_status"
  }
}
