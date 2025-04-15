package com.smileidentity.kmp.utils

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

actual fun hmacSha256(key: ByteArray, data: ByteArray): ByteArray {
  val mac = Mac.getInstance("HmacSHA256")
  val secretKey = SecretKeySpec(key, "HmacSHA256")
  mac.init(secretKey)
  return mac.doFinal(data)
}
