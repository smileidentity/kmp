package com.smileidentity.kmp.presentation.enhanced_document_verification

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import com.smileidentity.SmileID
import com.smileidentity.compose.theme.colorScheme
import com.smileidentity.compose.theme.typography
import java.io.File

actual class KmpFile(val file: File)


@Composable
actual fun getSmileIDColorScheme(): ColorScheme {
    return SmileID.colorScheme
}

@Composable
actual fun getSmileIDTypography(): Typography {
    return SmileID.typography
}

