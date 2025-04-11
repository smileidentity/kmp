package com.smileidentity.kmp.presentation

import androidx.compose.runtime.Composable
import com.smileidentity.SmileID
import com.smileidentity.compose.EnhancedDocumentVerificationScreen

@Composable
actual fun ComposeSmartSelfieEnrollment() {
    SmileID.EnhancedDocumentVerificationScreen(countryCode = "KE")
}