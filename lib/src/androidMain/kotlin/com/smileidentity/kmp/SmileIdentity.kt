package com.smileidentity.kmp

import android.content.Context
import com.smileidentity.SmileID

actual class SmileIdentity {
    actual fun initialize(value: Any?) {
        require(value != null) { "You should pass a context during initialization" }
        require(value is Context) { "Argument {value} should be a context" }
        SmileID.initialize(value)
    }
}
