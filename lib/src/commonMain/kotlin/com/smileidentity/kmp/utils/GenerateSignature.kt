package com.smileidentity.kmp.utils

import com.smileidentity.kmp.config.BuildKonfig
import kotlinx.datetime.Clock
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.time.ExperimentalTime

expect fun hmacSha256(key: ByteArray, data: ByteArray): ByteArray

@OptIn(ExperimentalTime::class, ExperimentalEncodingApi::class)
fun generateSignature(): Pair<String, String> {

  try {
    // Generate Timestamp
    val timestamp: String = Clock.System.now().toString()

    // Generate HMAC-SHA256 Signature
    val message = "$timestamp${BuildKonfig.SMILE_ID_PARTNER_ID}" + "sid_request"
    val signatureBytes =
      hmacSha256(BuildKonfig.SMILE_ID_API_KEY.encodeToByteArray(), message.encodeToByteArray())

    // Base64 Encode Signature
    val signature = Base64.encode(signatureBytes)

    return Pair(signature, timestamp)
  } catch (e: Exception) {

    e.printStackTrace()
    throw RuntimeException("Failed to generate signature")
  }
}
