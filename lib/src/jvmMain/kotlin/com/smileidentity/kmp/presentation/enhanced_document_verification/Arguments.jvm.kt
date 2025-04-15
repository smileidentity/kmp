package com.smileidentity.kmp.presentation.enhanced_document_verification

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import java.io.File

/** @TODO - to implement this */
actual class KmpFile(val file: File)

/** @TODO - to implement this */
@Composable
actual fun getSmileIDColorScheme(): ColorScheme {
  return lightColorScheme()
}

/** @TODO - to implement this */
@Composable
actual fun getSmileIDTypography(): Typography {
  return Typography()
}
