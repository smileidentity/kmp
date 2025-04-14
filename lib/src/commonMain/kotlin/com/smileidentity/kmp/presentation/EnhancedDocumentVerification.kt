package com.smileidentity.kmp.presentation

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.smileidentity.kmp.config.SmileIdentity
import com.smileidentity.kmp.presentation.enhanced_document_verification.ComposeConsentContent
import com.smileidentity.kmp.presentation.enhanced_document_verification.EnhancedDocumentVerificationResult
import com.smileidentity.kmp.presentation.enhanced_document_verification.KmpFile
import com.smileidentity.kmp.presentation.enhanced_document_verification.SmileIDResult
import com.smileidentity.kmp.presentation.enhanced_document_verification.getSmileIDColorScheme
import com.smileidentity.kmp.presentation.enhanced_document_verification.getSmileIDTypography
import com.smileidentity.kmp.utils.getCurrentIsoTimestamp
import com.smileidentity.kmp.utils.randomJobId
import com.smileidentity.kmp.utils.randomUserId
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf


@Composable
fun EnhancedDocumentVerification(
    countryCode: String,
    modifier: Modifier = Modifier,
    documentType: String? = null,
    captureBothSides: Boolean = true,
    idAspectRatio: Float? = null,
    bypassSelfieCaptureWithFile: KmpFile? = null,
    userId: String = rememberSaveable { randomUserId() },
    jobId: String = rememberSaveable { randomJobId() },
    allowNewEnroll: Boolean = false,
    showAttribution: Boolean = true,
    allowAgentMode: Boolean = false,
    allowGalleryUpload: Boolean = false,
    showInstructions: Boolean = true,
    useStrictMode: Boolean = false,
    extraPartnerParams: ImmutableMap<String, String> = persistentMapOf(),
    consentInformation: ComposeConsentContent = ComposeConsentContent(
        consentGrantedDate = getCurrentIsoTimestamp(),
        personalDetailsConsentGranted = false,
        contactInfoConsentGranted = false,
        documentInfoConsentGranted = false,
    ),
    colorScheme: ColorScheme = getSmileIDColorScheme(),
    typography: Typography = getSmileIDTypography(),
    onResult: (SmileIDResult<EnhancedDocumentVerificationResult>) -> Unit = {}
) {
    SmileIdentity.getInstance()
    ComposeEnhancedDocumentVerification(
        countryCode = countryCode,
        modifier = modifier,
        documentType = documentType,
        captureBothSides = captureBothSides,
        idAspectRatio = idAspectRatio,
        bypassSelfieCaptureWithFile = bypassSelfieCaptureWithFile,
        userId = userId,
        jobId = jobId,
        allowNewEnroll = allowNewEnroll,
        showAttribution = showAttribution,
        allowAgentMode = allowAgentMode,
        allowGalleryUpload = allowGalleryUpload,
        showInstructions = showInstructions,
        useStrictMode = useStrictMode,
        extraPartnerParams = extraPartnerParams,
        consentInformation = consentInformation,
        colorScheme = colorScheme,
        typography = typography,
        onResult = onResult
    )
}

@Composable
expect fun ComposeEnhancedDocumentVerification(
    countryCode: String,
    modifier: Modifier = Modifier,
    documentType: String? = null,
    captureBothSides: Boolean = true,
    idAspectRatio: Float? = null,
    bypassSelfieCaptureWithFile: KmpFile? = null,
    userId: String = rememberSaveable { randomUserId() },
    jobId: String = rememberSaveable { randomJobId() },
    allowNewEnroll: Boolean = false,
    showAttribution: Boolean = true,
    allowAgentMode: Boolean = false,
    allowGalleryUpload: Boolean = false,
    showInstructions: Boolean = true,
    useStrictMode: Boolean = false,
    extraPartnerParams: ImmutableMap<String, String> = persistentMapOf(),
    consentInformation: ComposeConsentContent = ComposeConsentContent(
        consentGrantedDate = getCurrentIsoTimestamp(),
        personalDetailsConsentGranted = false,
        contactInfoConsentGranted = false,
        documentInfoConsentGranted = false,
    ),
    colorScheme: ColorScheme = getSmileIDColorScheme(),
    typography: Typography = getSmileIDTypography(),
    onResult: (SmileIDResult<EnhancedDocumentVerificationResult>) -> Unit = {}
)

