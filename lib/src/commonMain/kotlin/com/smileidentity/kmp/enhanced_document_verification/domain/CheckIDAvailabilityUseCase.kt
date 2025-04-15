package com.smileidentity.kmp.enhanced_document_verification.domain

import arrow.core.Either
import com.smileidentity.kmp.enhanced_document_verification.api.EnhancedDocumentVerificationApi
import com.smileidentity.kmp.enhanced_document_verification.models.CheckIDAPIStatusRequest
import com.smileidentity.kmp.enhanced_document_verification.models.CheckIDAPIStatusResponse

/**
 * Use case responsible for checking the availability status of ID types for a given country.
 *
 * This class acts as an abstraction layer over the [EnhancedDocumentVerificationApi], enabling
 * separation of concerns and making it easier to manage business logic independently from the API
 * implementation.
 */
class CheckIDAvailabilityUseCase() {
  private val api = EnhancedDocumentVerificationApi()

  suspend operator fun invoke(
    request: CheckIDAPIStatusRequest
  ): Either<Throwable, CheckIDAPIStatusResponse> {
    return api.checkIDApiStatus(request)
  }
}
