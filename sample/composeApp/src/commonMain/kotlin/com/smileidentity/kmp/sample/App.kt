package com.smileidentity.kmp.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.smileidentity.kmp.presentation.EnhancedDocumentVerification
import com.smileidentity.kmp.presentation.enhanced_document_verification.SmileIDResult
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

            EnhancedDocumentVerification(
                onResult = { result ->
                    when (result) {
                        is SmileIDResult.Success -> {}
                        is SmileIDResult.Error -> {}
                    }
                })
        }

    }
}
