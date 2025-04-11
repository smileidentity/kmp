package com.smileidentity.kmp.presentation

import androidx.compose.runtime.Composable

import com.smileidentity.kmp.config.SmileIdentity


@Composable
fun SmartSelfieEnrollment() {
    SmileIdentity.getInstance()
    ComposeSmartSelfieEnrollment()
}

@Composable
expect fun ComposeSmartSelfieEnrollment()