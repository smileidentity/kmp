package com.smileidentity.kmp.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Returns the current timestamp in ISO 8601 format with millisecond precision in UTC.
 *
 * The format follows the pattern: `yyyy-MM-dd'T'HH:mm:ss.SSS'Z'`
 *
 * @return A string representing the current UTC timestamp in ISO 8601 format.
 */
fun getCurrentIsoTimestamp(): String {
  return Clock.System.now().toLocalDateTime(TimeZone.UTC).toString() + "Z"
}
