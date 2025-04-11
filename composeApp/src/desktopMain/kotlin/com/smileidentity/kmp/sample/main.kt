package com.smileidentity.kmp.sample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Smile ID",
    ) {
        App()
    }
}