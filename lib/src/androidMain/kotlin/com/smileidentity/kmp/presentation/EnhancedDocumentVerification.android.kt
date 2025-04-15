package com.smileidentity.kmp.presentation

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.smileidentity.SmileID
import com.smileidentity.compose.EnhancedDocumentVerificationScreen
import com.smileidentity.kmp.presentation.enhanced_document_verification.ComposeConsentContent
import com.smileidentity.kmp.presentation.enhanced_document_verification.EnhancedDocumentVerificationResult
import com.smileidentity.kmp.presentation.enhanced_document_verification.KmpFile
import com.smileidentity.kmp.presentation.enhanced_document_verification.SmileIDResult
import com.smileidentity.models.ConsentInformation
import kotlinx.collections.immutable.ImmutableMap
import java.io.File

@Composable
actual fun ComposeEnhancedDocumentVerification(
  countryCode: String,
  modifier: Modifier,
  documentType: String?,
  captureBothSides: Boolean,
  idAspectRatio: Float?,
  bypassSelfieCaptureWithFile: KmpFile?,
  userId: String,
  jobId: String,
  allowNewEnroll: Boolean,
  showAttribution: Boolean,
  allowAgentMode: Boolean,
  allowGalleryUpload: Boolean,
  showInstructions: Boolean,
  useStrictMode: Boolean,
  extraPartnerParams: ImmutableMap<String, String>,
  consentInformation: ComposeConsentContent,
  colorScheme: ColorScheme,
  typography: Typography,
  onResult: (SmileIDResult<EnhancedDocumentVerificationResult>) -> Unit,
) {
  SmileID.EnhancedDocumentVerificationScreen(
    countryCode = countryCode,
    captureBothSides = captureBothSides,
    documentType = documentType,
    bypassSelfieCaptureWithFile = bypassSelfieCaptureWithFile as File?,
    jobId = jobId,
    userId = userId,
    allowNewEnroll = allowNewEnroll,
    showAttribution = showAttribution,
    allowAgentMode = allowAgentMode,
    allowGalleryUpload = allowGalleryUpload,
    showInstructions = showInstructions,
    useStrictMode = useStrictMode,
    extraPartnerParams = extraPartnerParams,
    consentInformation =
      ConsentInformation(
        consentGrantedDate = consentInformation.consentGrantedDate,
        personalDetailsConsentGranted = consentInformation.personalDetailsConsentGranted,
        contactInfoConsentGranted = consentInformation.contactInfoConsentGranted,
        documentInfoConsentGranted = consentInformation.documentInfoConsentGranted,
      ),
    colorScheme = colorScheme,
    typography = typography,
    onResult = { sdkResult ->
      val kmpResult =
        when (sdkResult) {
          is com.smileidentity.results.SmileIDResult.Success -> {
            SmileIDResult.Success(
              EnhancedDocumentVerificationResult(
                selfieFile = KmpFile(sdkResult.data.selfieFile),
                documentFrontFile = KmpFile(sdkResult.data.documentFrontFile),
                livenessFiles = sdkResult.data.livenessFiles?.map { KmpFile(it) },
                documentBackFile = sdkResult.data.documentBackFile?.let { KmpFile(it) },
                didSubmitEnhancedDocVJob = sdkResult.data.didSubmitEnhancedDocVJob,
              )
            )
          }

          is com.smileidentity.results.SmileIDResult.Error -> {
            SmileIDResult.Error(sdkResult.throwable.toString())
          }
        }
      onResult(kmpResult)
    },
  )
}
