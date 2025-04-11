package com.smileidentity.kmp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.smileidentity.kmp.config.BuildKonfig

@Composable
actual fun ComposeSmartSelfieEnrollment() {
    LaunchedEffect(Unit) {
        openSmileLink()
    }
}


fun openSmileLink() {
    val url = BuildKonfig.SMILE_ID_SMILE_LINK

    try {
        val os = System.getProperty("os.name").lowercase()
        when {
            os.contains("win") -> Runtime.getRuntime()
                .exec("rundll32 url.dll,FileProtocolHandler $url")

            os.contains("mac") -> Runtime.getRuntime().exec("open $url")
            os.contains("nix") || os.contains("nux") || os.contains("bsd") -> Runtime.getRuntime()
                .exec("xdg-open $url")

            else -> throw UnsupportedOperationException("Cannot open URL on this OS")
        }
    } catch (e: Exception) {
        println("Error opening browser: ${e.message}")
    }
}
