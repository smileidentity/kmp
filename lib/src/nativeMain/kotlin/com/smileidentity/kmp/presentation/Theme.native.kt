package com.smileidentity.kmp.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.smileidentity.kmp.presentation.enhanced_document_verification.getSmileIDColorScheme
import com.smileidentity.kmp.presentation.enhanced_document_verification.getSmileIDTypography

@Composable
actual fun SmileIDTheme(content: @Composable () -> Unit) {
  MaterialTheme(
    colorScheme = getSmileIDColorScheme(),
    typography = getSmileIDTypography(),
    content = content,
  )
}
