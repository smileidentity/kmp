package com.smileidentity.kmp.presentation

import androidx.compose.runtime.Composable

import com.smileidentity.kmp.config.SmileIdentity


@Composable
fun EnhancedDocumentVerification() {
    SmileIdentity.getInstance()
    ComposeEnhancedDocumentVerification()
}

@Composable
expect fun ComposeEnhancedDocumentVerification()