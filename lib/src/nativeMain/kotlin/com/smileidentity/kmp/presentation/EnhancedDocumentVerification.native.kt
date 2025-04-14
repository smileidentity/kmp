package com.smileidentity.kmp.presentation

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.smileidentity.kmp.presentation.enhanced_document_verification.ComposeConsentContent
import com.smileidentity.kmp.presentation.enhanced_document_verification.EnhancedDocumentVerificationResult
import com.smileidentity.kmp.presentation.enhanced_document_verification.KmpFile
import com.smileidentity.kmp.presentation.enhanced_document_verification.SmileIDResult
import kotlinx.collections.immutable.ImmutableMap

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
    onResult: (SmileIDResult<EnhancedDocumentVerificationResult>) -> Unit
) {

}


